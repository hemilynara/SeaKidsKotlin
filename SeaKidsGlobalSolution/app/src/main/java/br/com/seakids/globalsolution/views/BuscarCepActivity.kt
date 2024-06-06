package br.com.seakids.globalsolution.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.seakids.globalsolution.R
import br.com.seakids.globalsolution.ViaCepService
import br.com.seakids.globalsolution.data.model.Endereco
import br.com.seakids.globalsolution.data.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscarCepActivity : AppCompatActivity() {

    private lateinit var etCep: EditText
    private lateinit var btnBuscar: Button
    private lateinit var tvResultado: TextView
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_cep)

        etCep = findViewById(R.id.editTextCep)
        btnBuscar = findViewById(R.id.buttonBuscar)
        tvResultado = findViewById(R.id.textViewResultado)
        btnLogin = findViewById(R.id.buttonlogin)

        btnBuscar.setOnClickListener {
            val cep = etCep.text.toString()
            if (cep.isNotEmpty()) {
                buscarCep(cep)
            } else {
                tvResultado.text = "Por favor, insira um CEP."
            }
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

    private fun buscarCep(cep: String) {
        val service = RetrofitClient.instance.create(ViaCepService::class.java)
        val call = service.buscarCep(cep)

        call.enqueue(object : Callback<Endereco> {
            override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                if (response.isSuccessful) {
                    val endereco = response.body()
                    endereco?.let {
                        tvResultado.text = """
                            CEP: ${it.cep}
                            Logradouro: ${it.logradouro}
                            Complemento: ${it.complemento}
                            Bairro: ${it.bairro}
                            Localidade: ${it.localidade}
                            UF: ${it.uf}
                        """.trimIndent()
                    } ?: run {
                        tvResultado.text = "CEP não encontrado."
                    }
                } else {
                    tvResultado.text = "Erro na resposta: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<Endereco>, t: Throwable) {
                Log.e("BuscarCepActivity", "Erro na chamada", t)
                tvResultado.text = "Erro na chamada: ${t.message}"
            }
        })
    }
}
