package com.mars.auth.entity;


import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DynamicInsert
@Entity
public class ViewMenus {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private boolean enable = true;
	private String created_by;
	private Date created_at;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_view")
	private Views view;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_menu")
	private Menus menu;
	
	public ViewMenus(Views view, Menus menu, String created_by) {
		this.view = view;
		this.menu = menu;
		this.created_by = created_by;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ViewMenus that = (ViewMenus) o;
        return Objects.equals(view, that.view) &&
               Objects.equals(menu, that.menu);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(view, menu);
    }
	
}
