package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBootAppApplication.class, args);
	}
	
//	@RequestMapping("/")
//    public String hello(HttpServletRequest request, HttpServletResponse response) {
//		response.setContentType(MediaType.TEXT_HTML_VALUE);
//		String content = """
//				<html>
//				<head>
//					<title>index</title>
//				</head>
//				<body>
//					<h1>Sample App</h1>
//					<p>This is sample app page!</p>
//				</body>
//				</html>
//		""";
//        return content;
//    }
//	
//	DataObject[] data = {
//			new DataObject("noname","no email address",0),
//			new DataObject("taro","taro@yamada",39),
//			new DataObject("hanako","hanako@flower",28),
//			new DataObject("sachiko","sachiko@happy",17),
//			new DataObject("jiro","jiro@change",6)
//	};
//	
//	@GetMapping("/{num}")
//    public DataObject index(@PathVariable int num) {
//		int n = num < 0 ? 0 : num >= data.length ? 0 : num;
//        return data[n];
//    }
//	
//	class DataObject {
//		private String name;
//		private String mail;
//		private int age;
//		
//		public DataObject(String name, String mail, int age) {
//			super();
//			this.setName(name);
//			this.setMail(mail);
//			this.setAge(age);
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
//		public int getAge() {
//			return age;
//		}
//
//		public void setAge(int age) {
//			this.age = age;
//		}
//	}

}
