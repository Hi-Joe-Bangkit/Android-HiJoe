package id.capstone.hijoe.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import id.capstone.hijoe.R;
import id.capstone.hijoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupView();
    }

    private void setupView() {
        binding.btnOpenCamera.setOnClickListener(v -> {
            Toast.makeText(this, "open camera", Toast.LENGTH_SHORT).show();
        });

        binding.btnOpenGallery.setOnClickListener(v -> {
            Toast.makeText(this, "open gallery", Toast.LENGTH_SHORT).show();
        });
    }
}