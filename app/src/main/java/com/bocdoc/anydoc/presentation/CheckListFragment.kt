package com.bocdoc.anydoc.presentation

import androidx.navigation.fragment.findNavController
import com.bocdoc.anydoc.R
import com.bocdoc.anydoc.coreui.base.BindingFragment
import com.bocdoc.anydoc.data.dto.CheckListDto
import com.bocdoc.anydoc.databinding.FragmentAiResultBinding
import com.bocdoc.anydoc.databinding.FragmentChecklistBinding
import com.bocdoc.anydoc.presentation.adapter.ChecklistRadioAdapter

class CheckListFragment : BindingFragment<FragmentChecklistBinding>(R.layout.fragment_checklist) {

    override fun initView() {
        setCheckList()
        clickBackButton()
    }


    private fun setCheckList() {
        val adapter = ChecklistRadioAdapter()
        binding.rcvChecklistRecyclerview.adapter = adapter

        adapter.submitList(checkList)
    }

    private fun clickBackButton() {
        binding.ivChecklistBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private val checkList = listOf(
        CheckListDto(
            title = "\uD83D\uDEBF 2주에 한번 샤워시키기",
            description = "애옹이가 싫다고 해도 2주에 한번씩은 샤워를 씻겨줘야 비듬, 각질을 예방할 수 있어요!",
            check = false,
        ),
        CheckListDto(
            title = "\uD83E\uDDFA 생활반경 주변 정돈",
            description = "애옹이의 생활반경이 어떻게 되는지 파악해주시고, 그 생활반경의 청결을 유지해주시면 비듬, 각질을 줄일 수 있어요!",
            check = false,
        ),
        CheckListDto(
            title = "✍ 증상 기록하기",
            description = "애옹이의 증상이 어땠는지, 초기 증상에 대한 영상을 찍어주시는 게 애옹이의 추후 진단에 도움이 됩니다.",
            check = false,
        ),
        CheckListDto(
            title = "\uD83E\uDD7C 피부과 내원 후, 진단받기",
            description = "애옹이의 증상은 이렇지만, 실제 진단은 어떻게 나올지 몰라요! 피부과 내원 후 애옹이의 실제 진단을 받아주세요:)",
            check = false,
        ),
    )
}