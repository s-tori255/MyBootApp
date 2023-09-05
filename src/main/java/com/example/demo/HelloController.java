package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.PersonRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Controller
public class HelloController {
	
	@Autowired
	PersonRepository repository;
	
	@GetMapping("/")
    public ModelAndView index(
    		@ModelAttribute("formModel") Person Person,
    		ModelAndView mav) {
    	mav.setViewName("index");
    	mav.addObject("title", "Hello page");
    	mav.addObject("msg", "this is JPA sample data.");
    	Iterable<Person> list = repository.findAll();
    	mav.addObject("data", list);
        return mav;
    }
	
	@PostMapping("/")
    @Transactional
    public ModelAndView form(
    		@ModelAttribute("formModel") @Validated Person Person,
    		BindingResult result,
    		ModelAndView mav) {
		ModelAndView res = null;
		System.out.println(result.getFieldErrors());
		if(!result.hasErrors()) {
			repository.saveAndFlush(Person);
			res = new ModelAndView("redirect:/");
		}else {
			mav.setViewName("index");
			mav.addObject("title", "Hello page");
			mav.addObject("msg","sorry, error is occured...");
			Iterable<Person> list = repository.findAll();
			mav.addObject("data",list);
			res = mav;
		}
        return res;
    }
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(
    		@ModelAttribute("formModel") Person Person,
    		@PathVariable int id,
    		ModelAndView mav) {
    	mav.setViewName("edit");
    	mav.addObject("title", "edit Person");
    	Optional<Person> data = repository.findById((long)id);
    	mav.addObject("formModel",data.get());
        return mav;
    }
    
    @PostMapping("/edit")
    @Transactional
    public ModelAndView update(
    		@ModelAttribute("formModel") Person Person,
    		ModelAndView mav) {
    	repository.saveAndFlush(Person);
        return new ModelAndView("redirect:/");
    }
    
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(
    		@ModelAttribute("formModel") Person Person,
    		@PathVariable int id,
    		ModelAndView mav) {
    	mav.setViewName("delete");
    	mav.addObject("title", "delete Person");
    	Optional<Person> data = repository.findById((long)id);
    	mav.addObject("formModel",data.get());
        return mav;
    }
    
    @PostMapping("/delete")
    @Transactional
    public ModelAndView remove(
    		@RequestParam long id,
    		ModelAndView mav) {
    	repository.deleteById(id);
        return new ModelAndView("redirect:/");
    }
	
//	@GetMapping("/")
//	public ModelAndView index(ModelAndView mav) {
//		mav.addObject("msg","message1<hr/>message2<br/>message3");
//		ArrayList<String[]> data = new ArrayList<String[]>();
//		data.add(new String[] {"taro","taro@yamada","090-999-999"});
//		data.add(new String[] {"hanako","hanako@flowers","080-888-888"});
//		data.add(new String[] {"sachiko","sachiko@happy","080-888-888"});
//		mav.addObject("data",data);
//		mav.setViewName("index");
//		return mav;
//	}
	
//	@GetMapping("/{num}")
//	public ModelAndView num(
//			@PathVariable int num,
//			ModelAndView mav) {
//		mav.addObject("msg","message1<hr/>message2<br/>message3");
//		mav.addObject("num",num);
//		ArrayList<DataObject> data = new ArrayList<DataObject>();
//		data.add(new DataObject(0,"taro","taro@yamada"));
//		data.add(new DataObject(1,"hanako","hanako@flowers"));
//		data.add(new DataObject(2,"sachiko","sachiko@happy"));
//		mav.addObject("data",data);
//		if(num >= 0) {
//			mav.addObject("check",num >= data.size() ? 0 : num);
//		} else {
//			mav.addObject("check",num <= data.size() * -1 ? 0 : num * -1);
//		}
//		mav.setViewName("index");
//		return mav;
//	}
//	
//	class DataObject{
//		private int id;
//		private String name;
//		private String mail;
//		
//		public DataObject(int id, String name, String mail) {
//			this.setId(id);
//			this.setName(name);
//			this.setMail(mail);
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public String getMail() {
//			return mail;
//		}
//
//		public void setMail(String mail) {
//			this.mail = mail;
//		}
//
//		public int getId() {
//			return id;
//		}
//
//		public void setId(int id) {
//			this.id = id;
//		}
//	}
	
//	@GetMapping("/{id}")
//	public ModelAndView ident(
//			@PathVariable int id,
//			ModelAndView mav) {
//		mav.addObject("msg","message1<hr/>message2<br/>message3");
//		mav.addObject("id",id);
//		mav.addObject("check",id % 2 == 0);
//		mav.addObject("trueVal","Even number!");
//		mav.addObject("falseVal","Odd number!");
//		mav.setViewName("index");
//		return mav;
//	}
	
//	@GetMapping("/home")
//	public ModelAndView home(ModelAndView mav) {
//		mav.addObject("msg","名前を書いてください。");
//		mav.setViewName("home");
//		return mav;
//	}
//	
//	@PostMapping("/")
//	public ModelAndView form(
//			@RequestParam(value="check1", required=false) boolean check1,
//			@RequestParam(value="radio1", required=false) String radio1,
//			@RequestParam(value="select1", required=false) String select1,
//			@RequestParam(value="select2", required=false) String[] select2,
//			ModelAndView mav) {
//		String res = "";
//		try {
//			res ="check: " + check1 + " radio: " + radio1 + " select:" + select1 + "\nselect2: ";
//		} catch (NullPointerException e){}
//		try {
//			res += select2[0];
//			for(int i = 0; i < select2.length; i++) {
//				res += ", " + select2[i];
//			}
//		}catch (NullPointerException e){
//			res += "null";
//		}
//		mav.addObject("msg",res);
//		mav.setViewName("index");
//		return mav;
//	}
	
	@PostConstruct
    public void init () {
        //１つ目のダミーデータ作成
        Person p1 = new Person();
        p1.setName("taro");
        p1.setAge(39);
        p1.setMail("taro@yamada");
        repository.saveAndFlush(p1);
        //２つ目のダミーデータ作成
        Person p2 = new Person();
        p2.setName("hanako");
        p2.setAge(28);
        p2.setMail("hanako@flower");
        repository.saveAndFlush(p2);
        //３つ目のダミーデータ作成
        Person p3 = new Person();
        p3.setName("sachico");
        p3.setAge(17);
        p3.setMail("sachico@happy");
        repository.saveAndFlush(p3);
    }

}
