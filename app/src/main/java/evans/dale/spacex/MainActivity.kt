package evans.dale.spacex


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import evans.dale.spacex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}