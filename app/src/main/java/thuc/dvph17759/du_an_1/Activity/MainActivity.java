package thuc.dvph17759.du_an_1.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

import thuc.dvph17759.du_an_1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getPaperOnboardingPageData());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_farme, paperOnboardingFragment);
        fragmentTransaction.commit();

        paperOnboardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                startActivity(new Intent(MainActivity.this, MainAppActivity.class));
            }
        });

    }

    private ArrayList<PaperOnboardingPage> getPaperOnboardingPageData(){
        PaperOnboardingPage scr1 = new PaperOnboardingPage("",
                "Dễ dàng mua sắm các thiết bị điện tử với Phone Store",
                Color.parseColor("#29b497"), R.drawable.step1, R.drawable.ic_phone);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("",
                "Những tính năng vượt trội trên các dòng máy mới",
                Color.parseColor("#2796ce"), R.drawable.step2, R.drawable.icon_step2);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("",
                "Những chiếc điện thoại mới nhất trên thị trường",
                Color.parseColor("#e25704"), R.drawable.step3, R.drawable.icnext);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);

        return elements;
    }
}