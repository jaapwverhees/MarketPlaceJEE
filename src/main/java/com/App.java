package com;

import com.controller.RegisterVisitorController;
import com.model.DeliveryOption;
import com.util.ErrorLogger;
import com.util.exeptions.CustomException;

import java.util.*;

public class App {

    public static void main(String[] args) throws Exception {

        new ErrorLogger().create(new CustomException("test"));
    }
}
