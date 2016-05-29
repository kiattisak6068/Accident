package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import play.libs.*;
import java.util.*;
import models.*;

import java.util.stream.Collectors;
import views.html.*;

import com.fasterxml.jackson.databind.*;

public class AccidentController extends Controller {

	public Result index(){
		return ok(home.render(""));		
	}
	
	public Result getform(){
		return ok(form.render());
	}
		
    public Result get() {
			List<Accident> acc = Accident.find.all();
			JsonNode json = Json.toJson(acc);
			return ok(Json.prettyPrint(json));
		}

	public Result search(Integer id) {
		Accident acc = Accident.find.byId(id); 
		if (acc == null) {
			return badRequest("no accident id: "+id);
		}
		return ok(Json.toJson(acc));
	}

}
