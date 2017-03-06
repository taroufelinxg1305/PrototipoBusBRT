package uis.brt.sensor.api;

public class Message {

	private String data;
	private String id;

	public Message(String id, String data) {
		super();
		this.id = id;
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
