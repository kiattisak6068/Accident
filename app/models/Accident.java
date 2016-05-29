package models;



import java.util.*;
import javax.persistence.*;
import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Accident extends Model {

    @Id
    public Integer ID;
	public String Description;
	public float Lat;
	public float Lng;
	public String time;
	@Formats.DateTime(pattern="dd/MM/yyyy")
    public Date date = new Date();
 
    public static Model.Finder<Integer, Accident> find = new Model.Finder<>(Accident.class);

}