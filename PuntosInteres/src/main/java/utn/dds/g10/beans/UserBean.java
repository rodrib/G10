package utn.dds.g10.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.fasterxml.jackson.databind.util.Converter;

@ManagedBean(name="user")
@SessionScoped
public class UserBean{
	
	public UserBean()
	{
		for (int i = 0; i < 20; i++) {
			listItems.add(new SelectItem(Integer.toString(i) ,"hola" + Integer.toString(i)));	
		}	
	}

	public String text = "This is Text!";
	public String htmlInput = "<input type='button' text='ejemplo' size='20' />";
	private List<SelectItem> listItems = new ArrayList<SelectItem>();
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHtmlInput() {
		return htmlInput;
	}
	public void setHtmlInput(String htmlInput) {
		this.htmlInput = htmlInput;
	}
	public List<SelectItem> getListItems() {
		return listItems;
	}
	public void setListItems(List<SelectItem> listItems) {
		this.listItems = listItems;
	}
	
	
}