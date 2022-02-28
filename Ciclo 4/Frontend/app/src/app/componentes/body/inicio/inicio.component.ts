import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UloginService } from 'src/app/services/ulogin.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})
export class InicioComponent implements OnInit {
  noticias: any
  ruta1= "../../../../assets/img/1.jpeg"
  ruta2= "../../../../assets/img/2.jpeg"
  ruta3= "../../../../assets/img/3.jpeg"
  ruta4= "../../../../assets/img/4.jpeg"

  titulo1= "Primera Comunion"
  titulo2= "Eventos educativos"
  titulo3= "Dia de la madre"
  titulo4= "Disfraces"

  descripcion1 = "Primeras comuniones en nuestra institucion"
  descripcion2= "Nuestros estudiantes aprendiendo de forma didactica"
  descripcion3= "Celebraciones de eventos populares"
  descripcion4= "Eventos de disfraces y recreacion"

  constructor(private uloginService: UloginService, private router: Router) { }

  ngOnInit(): void {
  }
  ObtenerNoticias() {
    this.uloginService.GetNoticias().subscribe((response: any) => {
      console.log(response)
      this.noticias = response

      let x =this.noticias.length//para mirar cuantas noticias hay
      /*
      this.ruta1 = this.noticias[(x-(1))].ruta
      this.ruta2 = this.noticias[(x-(2))].ruta
      this.ruta3 = this.noticias[(x-(3))].ruta
      this.ruta4 = this.noticias[(x-(4))].ruta
     // this.ruta5 = this.noticias[(x-(5))].ruta

      this.titulo1 = this.noticias[(x-(1))].tituloNoticia
      this.titulo2 = this.noticias[(x-(2))].tituloNoticia
      this.titulo3 = this.noticias[(x-(3))].tituloNoticia
      this.titulo4 = this.noticias[(x-(4))].tituloNoticia
    //  this.titulo5 = this.noticias[(x-(5))].tituloNoticia

      this.descripcion1 = this.noticias[(x-(1))].descripcion
      this.descripcion2 = this.noticias[(x-(2))].descripcion
      this.descripcion3 = this.noticias[(x-(3))].descripcion
      this.descripcion4 = this.noticias[(x-(4))].descripcion
     // this.descripcion5 = this.noticias[(x-(5))].descripcion
      */
    })

  }
  a = this.ObtenerNoticias()
 
}
