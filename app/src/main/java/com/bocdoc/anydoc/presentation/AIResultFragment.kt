package com.bocdoc.anydoc.presentation

import android.annotation.SuppressLint
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.coreui.view.ItemClick
import com.bocdoc.anydoc.data.dto.AiResultDetectedDto
import com.bocdoc.anydoc.data.dto.AiResultRadioDto
import com.bocdoc.anydoc.databinding.FragmentAiResultBinding
import com.bocdoc.anydoc.presentation.adapter.AiResultDetectedInfoAdapter
import com.bocdoc.anydoc.presentation.adapter.AiResultImgAdapter
import com.bocdoc.anydoc.presentation.adapter.AiResultRadioAdapter

class AIResultFragment : BindingFragment<FragmentAiResultBinding>(R.layout.fragment_ai_result) {

    override fun initView() {
        setImgList()
        setRadioList()
        setDetectedInfoList()
        clickBackButton()
        clickCheckList()
    }

    private fun setImgList() {
        val adapter = AiResultImgAdapter()
        binding.rcvAiResultImgRecyclerview.adapter = adapter

        adapter.submitList(imgList)
        clickImageItem(adapter)
    }

    private fun setRadioList() {
        val adapter = AiResultRadioAdapter()
        binding.rcvAiResultRadioRecyclerview.adapter = adapter

        adapter.submitList(radioList)
        clickRadioItem(adapter, radioList)
    }

    private fun setDetectedInfoList() {
        val adapter = AiResultDetectedInfoAdapter()
        binding.rcvAiResultItemDetectedRecyclerview.adapter = adapter

        adapter.submitList(clickedList)
    }

    private fun clickImageItem(adapter: AiResultImgAdapter) {
        adapter.aiResultImageClick = object : ItemClick {
            override fun onClick(view: View, position: Int) {
                val imgUrl = imgList[position]
                val bundle = bundleOf("imgUrl" to imgUrl)
                findNavController().navigate(R.id.action_ai_result_to_image, bundle)
            }
        }
    }

    private fun clickRadioItem(adapter: AiResultRadioAdapter, item: List<AiResultRadioDto>) {
        adapter.aiResultRadioClick = object : ItemClick {
            @SuppressLint("NotifyDataSetChanged")
            override fun onClick(view: View, position: Int) {
                clickedList =
                    when (position) {
                        0 -> {
                            detectedList
                        }
                        else -> {
                            detectedList.filter { it.detectedTitle == item[position].title }
                        }
                    }
                setDetectedInfoList()


                when(position){
                    0 -> {
                        item.get(0).check = true
                        item.get(1).check = false
                        item.get(2).check = false
                    }
                    1 -> {
                        item.get(0).check = false
                        item.get(1).check = true
                        item.get(2).check = false
                    }
                    2 -> {
                        item.get(0).check = false
                        item.get(1).check = false
                        item.get(2).check = true
                    }
                }

                adapter.notifyDataSetChanged()

            }
        }
    }

    private fun clickCheckList() {
        binding.tvAiResultChecklist.setOnClickListener {
            findNavController().navigate(R.id.action_ai_result_to_checklist)
        }
    }

    private fun clickBackButton() {
        binding.ivAiResultBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    // 임시 데이터들
    private val imgList = listOf(
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbzeQFE%2FbtsH0MrV64e%2FIIAUVza577u7NQwogH5lW1%2Fimg.png",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fdi6jUK%2FbtsH0AkQC7G%2FB2407CJCucyWI1BatWWDGK%2Fimg.png",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbtYSjn%2FbtsH1gMPPR2%2F8wOBqsyltGhLKGe6jrKD51%2Fimg.png",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FchLdsU%2FbtsH0pjDlmJ%2FjkwRt4NfuC5NIuv9eUMbMK%2Fimg.png",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbXJk69%2FbtsH0mmRi6j%2FPYRN5QrmszjfznjevCaQlK%2Fimg.png",
    )

    private val detectedList = listOf(
        AiResultDetectedDto(
            detectedTitle = "농포·여드름",
            detectedPercent = 79.0f,
            detectedReason = "사진 1,2에서 농포·여드름으로 의심이 되는 부분이 나왔습니다. 각 사진에서 탐지한 의심된 영역이 농포·여드름일 확률은 각각 60%, 79%, 73% 입니다."
        ),
        AiResultDetectedDto(
            detectedTitle = "결절·종괴",
            detectedPercent = 62.0f,
            detectedReason = "사진 3,4,5에서 결절·종괴으로 의심이 되는 부분이 나왔습니다. 각 사진에서 탐지한 의심된 영역이 결절·종괴일 확률은 각각 61%, 52%, 62% 입니다."
        ),
    )

    private var clickedList = detectedList

    private val radioList = listOf(
        AiResultRadioDto(
            title = "전체",
            check = true
        ),
        AiResultRadioDto(
            title = "농포·여드름",
            check = false
        ),
        AiResultRadioDto(
            title = "결절·종괴",
            check = false
        ),

    )

}