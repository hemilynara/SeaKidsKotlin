package br.com.seakids.globalsolution

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.seakids.globalsolution.data.model.Endereco
import br.com.seakids.globalsolution.data.retrofit.RetrofitClient
import br.com.seakids.globalsolution.views.CadastroActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Listener para o botão
        val buttonfirst: Button = findViewById(R.id.buttonfirst)
        buttonfirst.setOnClickListener {
            Log.d("MainActivity", "Button clicked")
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        buttonfirst.setOnClickListener {
            try {
                Log.d("MainActivity", "Button clicked")
                val intent = Intent(this, CadastroActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Erro ao abrir a tela de cadastro", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "Erro ao abrir a tela de cadastro: ${e.message}")
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

