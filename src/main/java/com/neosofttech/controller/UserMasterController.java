package com.neosofttech.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.neosofttech.model.LoginForm;
import com.neosofttech.model.Order;
import com.neosofttech.model.Address;
import com.neosofttech.model.CancelOrderReasons;
import com.neosofttech.model.UserMaster;
import com.neosofttech.service.ProductsService;
import com.neosofttech.service.UserMasterService;
import com.neosofttech.utils.InvalidUserException;
import com.neosofttech.utils.UserValidator;

@Controller
@SessionAttributes(names = { "username", "userID" })
public class UserMasterController extends DefaultController
{

	@Autowired
	UserMasterService userMasterService;
	@Autowired
	UserValidator userValidator;
	@Autowired
	ProductsService productsService;

	final static Logger logger = Logger.getLogger(UserMasterController.class);

	/* Method To Register A new User. */
	@RequestMapping(value = "Register", method = RequestMethod.POST)
	public ModelAndView register(@Valid @ModelAttribute("userMaster") UserMaster userMaster, BindingResult result,
			ModelMap model) {
		String viewName = "home";
		userValidator.validate(userMaster, result);
		if (result.hasErrors()) {
			viewName = "register";
			return new ModelAndView(viewName);
		}
		String id = userMasterService.save(userMaster);
		if (id.equalsIgnoreCase("username exits") || id.equalsIgnoreCase("email exits")) {
			model.addAttribute("error", id);
			logger.info("Cannot save because " + id);
			return new ModelAndView("register");
		} else {
			viewName = "login";
			model.addAttribute("loginForm", new LoginForm());
			model.addAttribute("error", "Successfully registered.<br>Please Login.");
			logger.info("New User:id = " + id);
			return new ModelAndView(viewName);
		}

	}

	@RequestMapping(value = "googleRegister", method = RequestMethod.POST)
	public @ResponseBody String gRegister(@RequestParam String name, @RequestParam String email, ModelMap model) {
		System.out.println("CALL KIA GOOGLE K REGISTER K LIYE");
		model.addAttribute("username", name);
		int roleId = 4;
		String s = userMasterService.socialLogin(name, email,roleId);
		if (s.equalsIgnoreCase("email exits")) {
			UserMaster u = userMasterService.getUserByEmail(email);
			model.addAttribute("userID", u.getId());
		} else {
			model.addAttribute("userID", Integer.valueOf(s));
			s = "Saved Successfully";
		}
		return s;
	}
	
	@RequestMapping(value = "facebookRegister", method = RequestMethod.POST)
	public @ResponseBody String fRegister(@RequestParam String name, 
			@RequestParam String email, ModelMap model) {
		System.out.println("CALL KIA Facebook K REGISTER K LIYE");
		model.addAttribute("username", name);
		int roleId=5;
		String s = userMasterService.socialLogin(name, email,roleId);
		if (s.equalsIgnoreCase("email exits")) {
			UserMaster u = userMasterService.getUserByEmail(email);
			model.addAttribute("userID", u.getId());
		} else {
			model.addAttribute("userID", Integer.valueOf(s));
			s = "Saved Successfully";
		}
		return s;
	}

	/* Method to logOut. */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout(ModelMap model, SessionStatus sts, HttpSession session) {
		String viewname = "home";
		model.remove("username");
		sts.setComplete();
		session.invalidate();
		model.addAttribute("message", "You have been Successfully Logged Out.");
		return new ModelAndView(viewname);
	}

	/* Method to Check a valid user */
	@RequestMapping(value = "Login", method = RequestMethod.POST)
	public ModelAndView login(ModelMap model,
			@Valid @ModelAttribute("loginForm") LoginForm loginForm,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("login");
		}
		logger.info("Login Attempt");
		String username = loginForm.getUsername();
		boolean flag = userMasterService.Login(loginForm);
		if (flag) {
			UserMaster u = userMasterService.findByUsername(username);
			model.addAttribute("username", username);
			model.addAttribute("userID", u.getId());
			logger.info("Successfull Login Attempt by " + u.getName());

			return new ModelAndView("redirect:/");
		} else {
			logger.info("UnSuccessfull Login Attempt by " + username);
			model.addAttribute("error", "Invalid Username or password");
			model.addAttribute("loginForm", new LoginForm());
			return new ModelAndView("login");
		}
	}

	@RequestMapping(value = "saveAddress", method = RequestMethod.POST)
	public @ResponseBody String saveAddress(@RequestParam String address,
			HttpSession session) 
	{
		int userid =(Integer) session.getAttribute("userID");
		userMasterService.addAdress(userid, address);
		return "successfully added";
	}
	@SuppressWarnings("unused")
	@RequestMapping(value = "user/saveAddress",method = RequestMethod.GET)
	public ModelAndView userSaveAddress(HttpSession session) throws NullPointerException 
	{
		try {
			int userid =(Integer) session.getAttribute("userID");
		}catch(NullPointerException e) {
			throw new NullPointerException();
		}
		return new ModelAndView("userSaveAddress");
	}
	@RequestMapping(value="user/saveUserAddress" ,method=RequestMethod.POST)
	public ModelAndView saveAdress(
			@RequestParam String pincode,
			@RequestParam String a1,
			@RequestParam String a2,
			@RequestParam String a3,
			@RequestParam String a4,
			HttpSession session) 
	{
		int id = (Integer) session.getAttribute("userID");
		String address = a1+"\r"+ a2+"\r"+ a3+"\r"+ a4+"\rPincode: "+ pincode;
		userMasterService.addAdress(id, address);
		return new ModelAndView("redirect:address");
	}
	
	@RequestMapping(value="user")
	public ModelAndView displayUserInfo(
			@RequestParam(value="message",required=false) String message,
			HttpSession session,ModelMap model)throws InvalidUserException
	{
		try {
		int id = (Integer) session.getAttribute("userID");
		List<Address> add = userMasterService.getAddress(id);
		List<Order> o = userMasterService.getOrderByUser(id);
		UserMaster user = userMasterService.getUser(id);
		model.addAttribute("User", user);
		model.addAttribute("Order", o);
		model.addAttribute("Address",add);
		model.addAttribute("message", message);
		}catch(NullPointerException e) {
			/*throw new InvalidUserException("Invalid Exception");*/
			return new ModelAndView("home","message","Login toh kar bhai");
		}
		return new ModelAndView("userInfo");
	}
	
	@RequestMapping(value="user/updateUser",method=RequestMethod.POST)
	public ModelAndView updateUser(@RequestParam(value="name") String name,
			@RequestParam(value="username") String username,
			@RequestParam(value="mobileno") String mobileno,
			@RequestParam(value="email") String email,
			HttpSession session,
			ModelMap model) 
	{
		int id= (Integer) session.getAttribute("userID");
		String status = userMasterService.updateUser(id,name,username,mobileno,email);
		return new ModelAndView("redirect:userAccount","message",status);
	}
	
	@RequestMapping(value="user/address",method=RequestMethod.GET)
	public ModelAndView userAddress(HttpSession session,ModelMap model) throws NullPointerException
	{try {
		int id= (Integer) session.getAttribute("userID");
		List<Address> a = userMasterService.getAddress(id);
		model.addAttribute("Address", a);
	}catch(NullPointerException e) {
		throw new NullPointerException();
	}
		return new ModelAndView("userAddress");
	}
	
	@RequestMapping(value="user/userOrder",method=RequestMethod.GET)
	public ModelAndView userOrder(ModelMap model,
			HttpSession session) throws NullPointerException
	{try {
		int id = (Integer) session.getAttribute("userID");
		List<Order> o = userMasterService.getOrderByUser(id);
		List<Order> pendingOrder =userMasterService.getOrderByStatusNUser(id, "Pending");
		List<Order> deliveredOrder =userMasterService.getOrderByStatusNUser(id, "Delivered");		
		model.addAttribute("pendingOrder", pendingOrder);
		model.addAttribute("CancelOrder", deliveredOrder);
		model.addAttribute("Order",o);
	}catch(NullPointerException e) {
		throw new NullPointerException();
	}
		return new ModelAndView("userOrder");
	}
	
	@RequestMapping(value="user/userAccount",method=RequestMethod.GET)
	public ModelAndView editOrder(@RequestParam(required=false) String message,
			ModelMap model,
			HttpSession session) 
	{
		try {
		int id = (Integer) session.getAttribute("userID");
		UserMaster user = userMasterService.getUser(id);
		model.addAttribute("User", user);
		model.addAttribute("message", message);
		}catch(NullPointerException e) {
			throw new NullPointerException();
		}
		return new ModelAndView("userAccountSetting");
	}
	@RequestMapping(value="user/Order/{orderId}/CancelOrder")
	public ModelAndView cancelOrder(@PathVariable int orderId,
			HttpSession session,
			ModelMap model) throws NullPointerException, InvalidUserException
	{
		try {
			int id = (Integer) session.getAttribute("userID");
			Order o = userMasterService.getOrderById(orderId);
			if(id!=o.getUser().getId()) throw new InvalidUserException("Galat user ne kia attempt");
			List<CancelOrderReasons> cor = userMasterService.getCancelKReasons();
			model.addAttribute("o", o);		
			model.addAttribute("reasons", cor);
		}catch(NullPointerException e) {
			throw new NullPointerException();
		}
		return new ModelAndView("cancelOrder");
	}
	
	@RequestMapping(value="user/ConfirmCancel",method=RequestMethod.POST)
	public ModelAndView confirmCancel(@RequestParam(name="id") int orderId,
			@RequestParam String reason,ModelMap model,
			HttpSession session) 
	{
		try {
		int userId=(Integer) session.getAttribute("userID");
		String s =userMasterService.cancelOrder(userId, orderId,reason);
		return new ModelAndView("redirect:/user","message",s);
		}catch(NullPointerException e) {
			throw new NullPointerException();
		}
		
	}
	@RequestMapping(value="user/RemoveUser",method=RequestMethod.GET)
	public ModelAndView removeUser(@RequestParam(required=false) String message,HttpSession session,ModelMap model) 
	{
		@SuppressWarnings("unused")
		int userId=(Integer) session.getAttribute("userID");
		model.addAttribute("message", message);
		return new ModelAndView("userRemove");
	}
	
	@RequestMapping(value="user/RemoveUser/ConfirmRemoveUser",method=RequestMethod.POST)
	public ModelAndView removeUser(@RequestParam String password,
			ModelMap model,SessionStatus sts,HttpSession session) 
	{
			int userId=(Integer) session.getAttribute("userID");
			UserMaster user = userMasterService.getUser(userId);
			if(password.equals(user.getPassword())) {
				userMasterService.removeUser(userId);
				logout(model, sts, session);
			return new ModelAndView("redirect:/home","message","Tera Account Delete Ho gaya hai");
			}else {
				return new ModelAndView("redirect:/user/RemoveUser","message","WrongPassword");
			}
				
	}
}