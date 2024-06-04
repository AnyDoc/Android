package com.bocdoc.anydoc.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.coreui.view.ItemClick
import com.bocdoc.anydoc.data.dto.AiResultDetectedDto
import com.bocdoc.anydoc.databinding.FragmentAiResultBinding
import com.bocdoc.anydoc.presentation.adapter.AiResultDetectedInfoAdapter
import com.bocdoc.anydoc.presentation.adapter.AiResultImgAdapter
import com.bocdoc.anydoc.presentation.adapter.AiResultRadioAdapter

class AIResultFragment : BindingFragment<FragmentAiResultBinding>(R.layout.fragment_ai_result) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImgList()
        setRadioList()
        setDetectedInfoList()
        clickBackButton()
    }

    private fun setImgList() {
        val adapter = AiResultImgAdapter()
        binding.rcvAiResultImgRecyclerview.adapter = adapter

        adapter.submitList(imgList)
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

    private fun clickRadioItem(adapter: AiResultRadioAdapter, item: List<String>) {
        adapter.aiResultRadioClick = object : ItemClick {
            override fun onClick(view: View, position: Int) {

                // TODO: 추후에 라디오 버튼 리스트로 변경하기
                clickedList = if (position == 0) {
                    detectedList
                } else {
                    detectedList.filter { it.detectedTitle == item[position] }
                }

                // TODO: 실제 통신 후, LiveData 로 변경해서 적용하기
                setDetectedInfoList()

                adapter.notifyItemChanged(adapter.selectedPosition)
            }
        }
    }

    private fun clickBackButton() {
        binding.ivAiResultBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    // 임시 데이터들
    private val imgList = listOf(
        "https://wallpapercrafter.com/th8002/834705-wildlife-Africa-animals-elephant-animal-wildlife.jpg",
        "https://blogfiles.pstatic.net/20151218_167/bmh8824gr_1450428440331O1tk1_PNG/%B1%CD%BF%A9%BF%EE_%C8%F1%B1%CD%B5%BF%B9%B0_%B8%F0%C0%BD_%BD%C5%B1%E2%C7%D1_%B5%BF%B9%B0_%BB%E7%C1%F8%B5%E901.png",
        "https://blogfiles.pstatic.net/20151218_290/bmh8824gr_1450428440553L6eph_PNG/%B1%CD%BF%A9%BF%EE_%C8%F1%B1%CD%B5%BF%B9%B0_%B8%F0%C0%BD_%BD%C5%B1%E2%C7%D1_%B5%BF%B9%B0_%BB%E7%C1%F8%B5%E902.png",
    )

    private val detectedList = listOf(
        AiResultDetectedDto(
            detectedTitle = "비듬,각질",
            detectedPercent = 93.23f,
            detectedReason = "이유이유이유이유"
        ),
        AiResultDetectedDto(
            detectedTitle = "증상3",
            detectedPercent = 93.23f,
            detectedReason = "이유이유이유이유"
        ),
        AiResultDetectedDto(
            detectedTitle = "증상4",
            detectedPercent = 93.23f,
            detectedReason = "이유이유이유이유"
        ),
        AiResultDetectedDto(
            detectedTitle = "증상4",
            detectedPercent = 93.23f,
            detectedReason = "이유이유이유이유"
        ),
        AiResultDetectedDto(
            detectedTitle = "증상4",
            detectedPercent = 93.23f,
            detectedReason = "이유이유이유이유"
        ),
    )

    private var clickedList = detectedList

    private val radioList = listOf("전체", "비듬,각질", "증상3", "증상4")

}