# EasyButton
Android library to quickly create buttons of different shapes, strokes, background and shadow effects.  
EasyButton extends the androidx AppCompatButton and provides additional layout attributes to create the desired button and helps in minimizing the number of Drawable  xml files required for each distinct button type contributing in cleaner code.
It renders/draws all background effects in a single optimized custom Drawable which is created at runtime during construction of the button view.

## Integration
### Gradle
Step 1. Add the JitPack repository to your build file 
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency to your app level build.gradle
  
    implementation 'com.github.rishirajput:EasyButton:1.0.0'

For Maven, sbt and leiningen
https://jitpack.io/#rishirajput/EasyButton   

## Usage:  


```xml
<com.rishirajput.easybutton.EasyButton
	android:id="@+id/easyButton"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:padding="20dp"
	android:text="@string/easy_button"
	android:textColor="@android:color/white"
	app:backgroundShape="rectangle"
	app:backgroundType="gradient"
	app:cornerRadius="40dp"
	app:defaultBackGroundColorBottom="@android:color/holo_red_dark"
	app:defaultBackGroundColorTop="@android:color/holo_red_light"
	app:pressedBackGroundColorBottom="@android:color/holo_red_light"
	app:pressedBackGroundColorTop="@android:color/holo_red_dark"/>
```
![alt text](https://github.com/rishirajput/EasyButton/blob/master/sample_images/easy_button_sample.PNG?raw=true)
![alt text](https://github.com/rishirajput/EasyButton/blob/master/sample_images/easy_button_sample_2.PNG?raw=true)
![alt text](https://github.com/rishirajput/EasyButton/blob/master/sample_images/easy_button_sample_3.PNG?raw=true)
