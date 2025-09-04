package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                TelaBlocoDeNotas()
            }
        }
    }

data class notas(var status: Boolean, val texto: String)

@Composable
fun TelaBlocoDeNotas(){

    var listaNotas = remember{
            mutableStateListOf(
                notas(false, "Android"),
                notas(false, "Android"),
                notas(false, "Android"),

        )}


    Column (modifier = Modifier.fillMaxSize()){
        Cabecalho()
        Spacer(modifier = Modifier.height(5.dp))

        Spacer(modifier = Modifier.height(5.dp))
        Formulario(onClickReturnText = {
            textoNota ->
            listaNotas.add(notas(false, textoNota))
        })

        LazyColumn{

            items(listaNotas){
                nota ->
                Nota(nota, onClickRemove = {
                    listaNotas.remove(nota)
                })
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

    }
}

@Preview
@Composable
fun Cabecalho(){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ){

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Icone Perfil",
                modifier = Modifier.size(80.dp),
                tint = Color.Blue
            )

            Spacer(modifier = Modifier.width(5.dp))

            Column{

                Text(
                    text = "Matheus Navalski"
                )

                Text(
                    text = "Programador",
                    style = MaterialTheme.typography.bodySmall
                )

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun Formulario(onClickReturnText: (String) -> Unit){

        var texto = remember{
            mutableStateOf("tarefa...")
        }

        Row(){
            TextField(value = texto.value, onValueChange = {texto.value = it})

            Button(onClick = {

                onClickReturnText(texto.value)
                texto.value =""
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Icon add"
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Add")
            }
    }

}

//@Preview
@Composable
fun Nota(nota: notas, onClickRemove: (notas) -> Unit){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp)
        ){
            Icon(
                imageVector = Icons.Rounded.ArrowForward,
                contentDescription = "Icone Perfil",
                modifier = Modifier.size(80.dp),
                tint = Color.Blue
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text (
                text = nota.texto,
                style = MaterialTheme.typography.titleLarge
            )

            Button(onClick = {
                    onClickRemove(notas.status =true)
            }) {


                Text("X")
            })
        }
    }

}