import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Alphamask extends PApplet {


// YI: I used alpha mask as the beginning of the object from processing examples
PImage img;
PImage imgMask;
int smallPoint, largePoint;

public void setup() {
  //want bigger size
  //size(640, 360);
   
  colorMode(HSB); //this is for change color
  blendMode(BLEND); //give different effect
  img = loadImage("blur.jpg"); //load my picture
  imgMask = loadImage("mask1.jpg");//load my mask, the default from alpha mask is a round shape, I used photoshop drew a heart, I also googled other masks which I didn't use in the end(mask 2 and 3)
  img.mask(imgMask);
  smallPoint = 4;
  largePoint = 100;
  imageMode(CENTER);
  noStroke();
  frameRate(10);
}

int col=0; //this is for change color
public void draw() {
  background(col,255,126);//this color more delicate
  //background(random(255), random(255), random(255));//I also tried random color but hated it
  noCursor();//I don't want see my mouse
  //from open processing website
  float pointillize = map(mouseX, 0, mouseY, smallPoint, largePoint);
  int x = PApplet.parseInt(random(img.width));
  int y = PApplet.parseInt(random(img.height));
  int pix = img.get(mouseX, mouseY);
  fill(pix, random(1000));
  ellipse(mouseX, mouseY, pointillize, pointillize);// from processing pointillism
    for (int i=20; i<=width-20; i+=100) {
    for (int j=20; j<=width-20; j+=100) {
    //float d = dist (mouseX, mouseY, width/2, height/2);
    float d = dist (mouseX, mouseY, i, j);
    float scaler = map(d, 800, 0, 250, 25);
  //finished with open processing website code
  
    //tint(255,0,0);//red tint
    //tint(random(255), random(255), random(255));//tried again tint with random color, too messy
    tint(255,126);
    //image(img, width/2, height/2, scaler, scaler); 
    //image(img, 0, 0);
    image(img, i, j, scaler, scaler);//how I scale those object
    //image(img, width/2, height/2);
    //image(img, mouseX, mouseY);
    }
  }
}

    public void mousePressed(){ 
    col+=20; 
    col%=255; 
    println(col); 
    } //this is for change color
  public void settings() {  size(1200, 1500);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#030303", "--stop-color=#FFFCFC", "Alphamask" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
