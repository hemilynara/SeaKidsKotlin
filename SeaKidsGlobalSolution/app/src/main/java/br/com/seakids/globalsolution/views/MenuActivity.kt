package br.com.seakids.globalsolution.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.seakids.globalsolution.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val buttonlogin: Button = findViewById(R.id.buttoncuriosidade)
        buttonlogin.setOnClickListener {
            val intent = Intent(this, CuriosidadeActivity::class.java)
            startActivity(intent)
        }

        val buttonvideo: Button = findViewById(R.id.buttonvideo)
        buttonvideo.setOnClickListener {
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
        }

        buttonlogin.setOnClickListener {
            try {
                val intent = Intent(this, CuriosidadeActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Erro ao abrir a tela de curiosidades", Toast.LENGTH_SHORT).show()
                Log.e("MenuActivity", "Erro ao abrir a tela de curiosidades: ${e.message}")
            }
        }

        buttonvideo.setOnClickListener {
            try {
                val intent = Intent(this, VideoActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Erro ao abrir a tela de vídeo", Toast.LENGTH_SHORT).show()
                Log.e("MenuActivity", "Erro ao abrir a tela de vídeo: ${e.message}")
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}