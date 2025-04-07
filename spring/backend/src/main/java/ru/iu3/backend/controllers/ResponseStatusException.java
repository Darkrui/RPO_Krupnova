/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ru.iu3.backend.controllers;

import org.springframework.http.HttpStatus;


public class ResponseStatusException extends Exception {

    public ResponseStatusException(HttpStatus httpStatus, String country_not_found) {
    }

}
