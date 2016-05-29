package controllers;


import models.*;
import play.mvc.*;

import views.html.*;
import play.data.*;

import java.util.List;

public class HomeController extends Controller {

	 public Result register() {
        Users users=new Users("","","","");
        return ok(signup.render("",users));
    }

	 public Result save_register(){
        DynamicForm formchk = Form.form().bindFromRequest();

        Form<Users> form = Form.form(Users.class).bindFromRequest();
        int i=0;
        try {
            Users us = form.get();
            if (us.pwd.length()>=8) {
                if(formchk.get("pwd2").equals(formchk.get("pwd"))){
                    for (Users p:Users.find.all()){
                        if (p.contains(us.email)){
                            i++;
                        }
                    }
                 if (i>0){
                        String fail="อีเมลล์นี้มีผู้ใช้งานแล้ว";
                        return ok(signup.render(fail,us));
                    }else {
                        us.save();
                        return ok(home.render(""));
                    }
                }else {
                    String fail="รหัสผ่านไม่ตรงกัน";
                    return ok(signup.render(fail,us));
                }
            }else {
                String fail="รหัสผ่านน้อยกว่า 8 ตัว";
                return ok(signup.render(fail,us));
            }


        } catch (Exception ex) {
			//
        }
 return ok();
    }
	


	
	public Result loginsubmit() {
        int chk=0;
         Form<Users> form = Form.form(Users.class).bindFromRequest();
         Users user=form.get();

        for (Users p:Users.find.all()){
            if (p.chklogin(user.email)&&p.chklogin(user.pwd)){
            chk=1;
            }
        }
        if(chk==1){
            session().clear();
            session("user", user.email);
            return ok();
        }

        //for admin
        DynamicForm ad=Form.form().bindFromRequest();
        int chkadmin=0;


        for (Employees p:Employees.find.all()){
            if (p.chkadmin(ad.get("email")) && p.chkadmin(ad.get("pwd"))){
                chkadmin=1;
            }
        }
        if(chkadmin==1){
            session().clear();
            session("admin", ad.get("email"));
            return ok();
        }

        return ok(home.render(""));

    }
    public Result logout() {
        session().remove("admin");
        session().remove("user");
        session().remove("userId");
        return ok(home.render(""));
    }

}
