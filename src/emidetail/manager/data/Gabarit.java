/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emidetail.manager.data;

/**
 *
 * @author DVAmod
 */
public class Gabarit {
        private float height = 0;
        private float width = 0;
        private int length = 0;
        
        public Gabarit () {
            
        }
        
        public Gabarit(float h, float w, int l) {
            height = h;
            width = w;
            length = l;
        }
        
        public float getH(){
            return height;
        }
        
        public float getW(){
            return width;
        }
        
        public int getL(){
            return length;
        }
    }
