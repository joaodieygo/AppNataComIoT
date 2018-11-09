package br.com.joaodieygo.appnatacomiot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        buttonCriarConta.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(
                    editTxtEmail.text.toString(),
                    editTxtSenha.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){
                    Toast.makeText(this@SignUpActivity,
                            "Usuário criado com sucesso!",
                            Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this@SignUpActivity,
                            "Erro ao criar o usuário!",
                            Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun salvarNoRealtimeDatabase() {
        val user = User(editTxtNome.text.toString(), editTxtEmail.text.toString(), editTxtCelular.text.toString())
        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                .setValue(user)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Erro ao criar o usuário!", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}
