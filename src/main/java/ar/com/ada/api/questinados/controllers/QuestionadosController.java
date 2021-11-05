package ar.com.ada.api.questinados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.questinados.entities.Pregunta;
import ar.com.ada.api.questinados.models.request.RespuestaAVerificar;
import ar.com.ada.api.questinados.models.response.RespuestaVerificada;
import ar.com.ada.api.questinados.services.QuestionadosService;

@RestController
public class QuestionadosController {

    @Autowired
    QuestionadosService service;

    @GetMapping("/questionados/next")
    public ResponseEntity<Pregunta> traerPreguntaRandom() {
    
        Pregunta proximaPregunta = service.traerPreguntaRandom();

        return ResponseEntity.ok(proximaPregunta);

        // return ResponseEntity.ok(service.traerPreguntaRandom()); 
    }

    @PostMapping("/questionados/verificaciones")
    public ResponseEntity<RespuestaVerificada> verificarRespuesta(@RequestBody RespuestaAVerificar respuestaAVerificar) {

        RespuestaVerificada respuestaVerificada = new RespuestaVerificada();

        if(service.verificarRespuesta(respuestaAVerificar.preguntaId, respuestaAVerificar.respuestaId)) {

            respuestaVerificada.esCorrecta = true;

        } else {

            respuestaVerificada.esCorrecta = false;
        }
        
        return ResponseEntity.ok(respuestaVerificada);
    }

}
