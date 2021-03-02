package com.example.musec;

import com.example.musec.Models.InstrumentEntity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InstrumentTest {
    private InstrumentEntity i;

    @Before
    public void setUp(){
        this.i=new InstrumentEntity();
    }

    @Test
    public void instrumentname(){
        assertEquals(false, this.i.setName("adri"));
        assertEquals("", this.i.getName());
        assertEquals(true, this.i.setName("antonio"));
        assertEquals("antonio", this.i.getName());

    }

    @Test
    public void instrumentdescription(){
        assertEquals(false, this.i.setDescription("hola"));
        assertEquals("", this.i.getDescription());
        assertEquals(true, this.i.setDescription("descripcion"));
        assertEquals("descripcion", this.i.getDescription());

    }

    @Test
    public void instrumentdate(){
        assertEquals(false, this.i.setDate("fecha"));
        assertEquals("", this.i.getDate());
        assertEquals(true, this.i.setDate("11/10/2011"));
        assertEquals("11/10/2011", this.i.getDate());

    }

    @Test
    public void instrumentprice(){
        assertEquals(false, this.i.setPrice("precio"));
        assertEquals("", this.i.getPrice());
        assertEquals(true, this.i.setPrice("100"));
        assertEquals("100", this.i.getPrice());

    }

    @Test
    public void instrumentimage(){
        this.i.setImage("precio");
        assertEquals("precio", this.i.getImage());
        this.i.setImage("asdf");
        assertEquals("asdf", this.i.getImage());

    }

    @Test
    public void instrumentbag(){
        this.i.setBag(true);
        assertEquals(true, this.i.isBag());
        this.i.setBag(false);
        assertEquals(false, this.i.isBag());

    }

    @Test
    public void instrumentstate(){
        this.i.setState("estado");
        assertEquals("estado", this.i.getState());
        this.i.setState("nuevo");
        assertEquals("nuevo", this.i.getState());

    }

    @Test
    public void instrumentid(){
        this.i.setId("1");
        assertEquals("1", this.i.getId());
        this.i.setId("2");
        assertEquals("2", this.i.getId());

    }




}
