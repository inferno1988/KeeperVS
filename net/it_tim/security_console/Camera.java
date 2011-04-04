package net.it_tim.security_console;

public class Camera {

private Long id;
private String description;
private String camera_url;

public void setId(Long id){this.id = id;}
public Long getId(){return id;}
public void setDescription(String description) { this.description = description; }
public String getDescription() { return this.description; }
public void setCameraURL(String cameraURL) { this.camera_url = cameraURL; }
public String getCameraURL() { return this.camera_url; }

}
