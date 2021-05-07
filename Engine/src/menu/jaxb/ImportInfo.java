package menu.jaxb;

import scheme.genreteClasses.RizpaStockExchangeDescriptor;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImportInfo {

    private final static String PACKAGE_NAME_FOR_JAXB = "scheme.genreteClasses";

    public RizpaStockExchangeDescriptor unmarshall(String path) throws FileNotFoundException, JAXBException {
        InputStream inputStream = new FileInputStream(new File(path));
        RizpaStockExchangeDescriptor descriptor = deserializeFrom(inputStream);

        return descriptor;
    }

    private static RizpaStockExchangeDescriptor deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(PACKAGE_NAME_FOR_JAXB);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (RizpaStockExchangeDescriptor) unmarshaller.unmarshal(in);
    }

    @Override
    public String toString() {
        return "ImportInfo{}" ;
    }
}
