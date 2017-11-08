import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.jinfeng.quxue.R;
/**
 * Created by JinFeng on 2017/8/14.
 */
public class Navlayout extends LinearLayout {
    public Navlayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.nav_header,this);

        Button btn1=(Button) findViewById(R.id.first_btn);
        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Heeeeeee", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
