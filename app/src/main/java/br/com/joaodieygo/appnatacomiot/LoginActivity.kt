package br.com.joaodieygo.appnatacomiot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        buttonCriarConta.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        buttonConectar.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                    editTxtEmail.text.toString(),
                    editTxtSenha.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){
                    vaiParaProximaTela()
                }else{
                    exibeErro()
                }
            }
        }
    }

    private fun exibeErro(){
        Toast.makeText(this@LoginActivity,
                "Email ou Senha inválidos!",
                Toast.LENGTH_LONG).show()
    }

    private fun vaiParaProximaTela(){
        startActivity(Intent(this, ControllActivity::class.java))
        finish()
    }
}
