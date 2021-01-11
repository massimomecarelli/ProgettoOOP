package model;

public class Request {
	double lat;
	double lon;
	int cnt;
	
	/*public Request(double lat, double lon, int cnt) {
		this.lat=lat;
		this.lon=lon;
		this.cnt=cnt;
	}*/
	
	public double getLat() {return lat;}
	public double getLon() {return lon;}
	public int getCnt() {return cnt;}
}
