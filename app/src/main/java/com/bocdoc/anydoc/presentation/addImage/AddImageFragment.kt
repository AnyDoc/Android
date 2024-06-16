package com.bocdoc.anydoc.presentation.addImage

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.coreui.fragment.toast
import com.bocdoc.anydoc.coreui.view.ItemClick
import com.bocdoc.anydoc.databinding.FragmentAddImageBinding
import com.bocdoc.anydoc.presentation.adapter.AiResultRadioAdapter

class AddImageFragment : BindingFragment<FragmentAddImageBinding>(R.layout.fragment_add_image) {

    private val imageList = ArrayList<Uri>()
    private lateinit var permissionManager: PermissionManager
    private lateinit var cameraGalleryManager: CameraGalleryManager
    private lateinit var imageFileManager: ImageFileManager
    private lateinit var addImgAdapter: AddImgAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAddImageItemAdapter()
    }

    override fun initView() {
        initSetText()
        initAddImageBtnClickListener()
    }

    private fun initAddImageItemAdapter() {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) // 페이지끼리 간격
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth) // 페이지 보이는 정도
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 가로 길이
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.viewpagerAddImage.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        binding.viewpagerAddImage.offscreenPageLimit = 1 // 미리 로드되는 페이지
        binding.viewpagerAddImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        addImgAdapter = AddImgAdapter(imageList)
        binding.viewpagerAddImage.adapter = addImgAdapter
        permissionManager = PermissionManager(requireActivity())
        cameraGalleryManager = CameraGalleryManager()
        imageFileManager = ImageFileManager(requireActivity())
        clickAddImage(addImgAdapter)
    }

    private fun initAddImageBtnClickListener() {
        binding.btnAddImageNext.setOnClickListener {
            findNavController().navigate(R.id.action_add_image_to_navigation_loading)
        }
    }

    private fun clickAddImage(adapter: AddImgAdapter){
        adapter.imageAddClick = object : ItemClick {
            override fun onClick(view: View, position: Int) {
                showImagePickerDialog() // 이미지 선택 다이얼로그 표시
            }
        }

    }
    private fun initSetText() {
        binding.tvAddImageCount.text =
            getString(R.string.tv_add_image_count, imageList.size, LIST_MAX_SIZE)
    }

    private fun showImagePickerDialog() {
        val dialog = SelectAddImageDialog(
            context = requireContext(),
            clickCamera = { openCamera() },
            clickGallery = { openGallery() }
        )
        dialog.show()
    }

    // 카메라
    private fun openCamera() {
        if (permissionManager.hasCameraPermission()) {
            if (imageList.size < LIST_MAX_SIZE) {
                startActivityForResult(cameraGalleryManager.getCameraIntent(), CAMERA_REQUEST_CODE)
            } else {
                // 이미지 최대 개수 초과 메시지 표시
                toast(R.string.tv_add_image_max.toString())
            }
        } else {
            permissionManager.requestCameraPermission()
        }
    }

    // 갤러리
    private fun openGallery() {
        if (permissionManager.hasStoragePermission()) {
            if (imageList.size < LIST_MAX_SIZE) {
                startActivityForResult(
                    cameraGalleryManager.getGalleryIntent(),
                    STORAGE_REQUEST_CODE
                )
            } else {
                // 이미지 최대 개수 초과 메시지 표시
                toast(R.string.tv_add_image_max.toString())
            }
        } else {
            permissionManager.requestStoragePermission()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    val extras = data?.extras
                    val bitmap = extras?.get("data") as? Bitmap
                    bitmap?.let {
                        val uri = imageFileManager.saveFile(
                            imageFileManager.randomFileName(),
                            "image/jpeg",
                            it
                        )
                        uri?.let {
                            imageList.add(it)
                            initSetText()
                            addImgAdapter.notifyDataSetChanged()
                        }
                    }
                }

                STORAGE_REQUEST_CODE -> {
                    val uri = cameraGalleryManager.getSelectedImageUri(data)
                    uri?.let {
                        imageList.add(it)
                        initSetText()
                        addImgAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    companion object {
        const val LIST_MAX_SIZE = 10
        const val CAMERA_REQUEST_CODE = 98
        const val STORAGE_REQUEST_CODE = 99
    }
}