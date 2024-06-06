package br.com.seakids.globalsolution.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.seakids.globalsolution.R
import com.google.android.material.snackbar.Snackbar

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        val buttonLogin: Button = findViewById(R.id.button_login)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val buttonBuscarCep: Button = findViewById(R.id.buttonbuscarcep)
        buttonBuscarCep.setOnClickListener {
            Log.d("CadastroActivity", "Buscar CEP button clicked")
            val intent = Intent(this, BuscarCepActivity::class.java)
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("CadastroActivity", "Erro ao iniciar Buscar CEP", e)
                val snackbar = Snackbar.make(buttonBuscarCep, "Erro ao iniciar Buscar CEP", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}



