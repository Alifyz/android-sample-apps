package alifyz.com.welcomeslider;

import android.os.Bundle;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.github.paolorotolo.appintro.model.SliderPage;

public class MainActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle("Slider Test");
        sliderPage.setDescription("Description some");
        int color = R.color.colorPrimary;

        addSlide(AppIntro2Fragment.newInstance("Teste", "Description", R.mipmap.ic_launcher, 4564));

        showSkipButton(false);
        setProgressButtonEnabled(true);
    }
}
