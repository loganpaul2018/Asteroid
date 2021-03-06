/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroides;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 *
 * @author 1DAW06
 */
public class Asteroides extends Application {
    Nave nave;
    Bala bala;
    Asteroide asteroide;
    public int resolucionX = 800;
    public int resolucionY = 400;
    @Override
    
    public void start(Stage primaryStage) {
       
        
        Pane root = new Pane();
        nave = new Nave(root);
        Scene scene = new Scene(root,resolucionX, resolucionY,Color.WHITE);
        scene.getStylesheets().add("resources/css/css.css");
        primaryStage.setTitle("Asteroides");
        primaryStage.setScene(scene);
        primaryStage.show();
        //crear lista asteroides
        ArrayList <Asteroide> listaAsteroide = new ArrayList();
        for (int i=0; i<10; i++) {
            asteroide = new Asteroide(root);
            listaAsteroide.add(asteroide);
            listaAsteroide.size();
        }
        ArrayList <Bala> listaBala = new ArrayList();
        AnimationTimer animationPolygon= new AnimationTimer(){

            
            public void colision(){
                    //Asteroide-Nave
                for (int i= 0; i <listaAsteroide.size(); i++) {
                    Shape colisionNaveAst = Shape.intersect(nave.getPolygon(),asteroide.getAsteroide());              
                    boolean colisionVaciaNaveAsteroide = colisionNaveAst.getBoundsInLocal().isEmpty();
                    System.out.println("colision "+colisionVaciaNaveAsteroide);
                    
                    if (colisionVaciaNaveAsteroide == false) {
                        root.getChildren().remove(nave.getPolygon());
                        System.out.println("muriste");
                    }

                }
            }
            
//            public void colision2(){
//                    //Asteroide-Bala
//                for (int i= 0; i <listaAsteroide.size(); i++) {
//                    Shape colisionNaveBA = Shape.intersect(asteroide.getAsteroide(), bala.getBala());
//                    boolean colisionVaciaBalaAsteroide = colisionNaveBA.getBoundsInLocal().isEmpty();
//
//                    if (colisionVaciaBalaAsteroide == false) {
//                        root.getChildren().remove(bala.getBala());
//                        root.getChildren().remove(asteroide.getAsteroide());
//                    }
//
//                }
//           }
        @Override   

            public void handle(long now) {
                //System.out.println("angulo " +nave.getAngulo());
                //System.out.println("direccionX "+nave.getDireccionX());
                //System.out.println("direccionY " +nave.getDireccionY());
                //System.out.println("velocidadX " +nave.getVelocidadNaveX());
                //System.out.println("velocidadY " +nave.getVelocidadNaveY());
                //System.out.println("giro " +nave.getVelocidadgiro());
                nave.naveMovimiento();
                nave.limites();
                nave.getVelocidadGirolimite();
                nave.detectorDeAnguloParaGirar();
                colision();
//              colision2();

                for (int i=0; i<listaAsteroide.size(); i++){
                    asteroide = listaAsteroide.get(i);
                    asteroide.movimientoAsteroide();
                    asteroide.posicion();
                    asteroide.setLimites();
                }

                for (int i=0; i<listaBala.size(); i++){
                    bala = listaBala.get(i);
                    bala.movimientoBala(nave);
                }
                scene.setOnKeyPressed((KeyEvent event) -> {
                    switch(event.getCode()) {
                        case LEFT:
                            nave.setAngulo1();
                            nave.rotacion();
                            nave.setVelocidadGiroconst();
                            break;

                        case RIGHT:
                            nave.setAngulo2();
                            nave.rotacion();
                            nave.setVelocidadGiroconst();
                            break;

                        case UP:
                            nave.setVelocidadNaveX();              
                            nave.setVelocidadNaveY();
                            nave.naveMovimiento();
                            nave.girarRad();
                            nave.fuegorl.setVisible(true);
                        break;

                        case SPACE:
                        bala = new Bala(root , nave);
                        bala.AnguloBala(nave);
                        listaBala.add(bala);
                    }

                });


            }
        };
    animationPolygon.start();
    }
}


