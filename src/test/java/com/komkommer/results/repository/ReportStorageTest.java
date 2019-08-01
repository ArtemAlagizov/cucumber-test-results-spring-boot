package com.komkommer.results.repository;

import com.komkommer.results.model.ElementList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReportStorageTest {
    @InjectMocks
    private ReportStorage reportStorage;

    @Test
    public void shallStoreReportWhenStoreReportIsCalled() {
        List<ElementList> elementLists = new ArrayList<>();
        ElementList elementList = new ElementList();

        elementList.setId("fake-id");
        elementLists.add(elementList);

        String dateTimeOfReportCreation = "01-01-2019";

        reportStorage.storeReport(elementLists, dateTimeOfReportCreation);

        assertEquals(reportStorage.getDateTimeOfReportCreation(), "01-01-2019");
        assertEquals(reportStorage.getElementList().get(0).getId(), "fake-id");
    }
}
