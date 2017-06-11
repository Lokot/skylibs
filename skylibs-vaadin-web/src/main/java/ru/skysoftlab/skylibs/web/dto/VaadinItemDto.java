package ru.skysoftlab.skylibs.web.dto;

public class VaadinItemDto {

	private Object obj;
	private String name;

	public VaadinItemDto(Object obj, String name) {
		this.obj = obj;
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	public <T> T getObj() {
		return (T) obj;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((obj == null) ? 0 : obj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(obj.getClass().equals(VaadinItemDto.class)){
			VaadinItemDto other = (VaadinItemDto) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (this.obj == null) {
				if (other.obj != null)
					return false;
			} else if (!this.obj.equals(other.obj))
				return false;
			return true;
		} else {
			if (this.obj.getClass() != obj.getClass())
				return false;
			if (this.obj == null) {
				return false;
			} else {
				return this.obj.equals(obj);
			}
		}
	}
	
}
