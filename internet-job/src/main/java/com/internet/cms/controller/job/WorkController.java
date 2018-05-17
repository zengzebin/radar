package com.internet.cms.controller.job;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.internet.cms.auth.AuthClass;
import com.internet.cms.model.job.Job;
import com.internet.cms.model.job.Work;
import com.internet.cms.page.Pager;
import com.internet.cms.service.job.IWorkService;

@RequestMapping("/admin/work")
@Controller
@AuthClass
public class WorkController {
	private IWorkService workService;

	public IWorkService getWorkService() {
		return workService;
	}
	@Inject
	public void setJobService(IWorkService jobService) {
		this.workService = jobService;
	}

	@RequestMapping("/works")
	public String listChild(Model model) {
		Pager<Work> jobs = workService.listWork();
		model.addAttribute("datas",jobs);
		return "work/list";
	}
	
	/**
	 * 跳转到添加界面
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new Job());
		return "work/add";
	}
	
	/**
	 * 执行添加操作
	 * @param pid
	 * @param job
	 * @param br
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Work job,Model model) {
		workService.add(job);
		return "redirect:/admin/work/works";
	}
	
	/**
	 * 跳转到任务更新界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		Work c = workService.load(id);
		model.addAttribute("job",c);
		return "work/update";
	}
	
	/**
	 * 更新任务
	 * @param id
	 * @param job
	 * @param br
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,Work job,BindingResult br,Model model) {
		if(br.hasErrors()) {	
			return "work/update";
		}
		workService.update(job);
		return "redirect:/admin/work/works";
	}
	
//	@RequestMapping("/delete/{id}")
//	public String delete(@PathVariable Integer id,Model model) {
//		workService.delete(id);
//		return "redirect:/admin/work/works";
//	}
//	
	
}
