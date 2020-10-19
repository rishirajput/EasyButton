# EasyButton
Android library to easily create buttons of different shapes, backgrounds and shadow effects.

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

Usage:
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
