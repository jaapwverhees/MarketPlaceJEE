package com.controller.DAO;

import com.model.Visitor;
import com.util.password.PasswordAuthentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VisitorDAOTest {

    @Mock
    EntityManager emMock;
    @Mock
    EntityTransaction entityTransactionMock;

    @Mock
    private PasswordAuthentication aut;

    @InjectMocks
    VisitorDAO dao = new VisitorDAO();


    @BeforeEach
    void setup() throws Exception {

    }

    @Test
    void getVisitor() throws Exception {
        when(emMock.find(any(), any())).thenReturn(new Visitor());

        dao.getVisitor("email");

        verify(emMock).find(any(), any());
    }

    @Test
    void createVisitor() throws Exception {
        when(emMock.getTransaction()).thenReturn(entityTransactionMock);
        doNothing().when(entityTransactionMock).begin();
        doNothing().when(entityTransactionMock).commit();

        Visitor visitor = new Visitor();
        visitor.setPassword("testtest");
        dao.createVisitor(visitor);

        verify(emMock).persist(isA(Visitor.class));
        verify(emMock, atLeastOnce()).getTransaction();
        verify(entityTransactionMock).begin();
        verify(entityTransactionMock).commit();
    }
}