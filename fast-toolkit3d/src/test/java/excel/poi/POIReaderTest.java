package excel.poi;

import com.thankjava.toolkit.bean.utils.TimeType;
import com.thankjava.toolkit3d.bean.excel.poi.FormatCellValueType;
import com.thankjava.toolkit3d.core.excel.poi.POIExcelBuilder;
import com.thankjava.toolkit3d.core.excel.poi.POIReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Desc: TODO
 * @Author: acexy@thankjava.com
 * @Date: 2019-06-04
 **/
public class POIReaderTest {

    public static void main(String[] args) throws FileNotFoundException {
        POIReader reader = new POIExcelBuilder(
                "/Users/acexy/Downloads/lanhai-merchant-info.xlsx"
        )
                .setSkipFirstRow()
//                .setPositionFormatCellValueType(2, 1, FormatCellValueType.DATE)
                .setDefaultTimeFormatType(TimeType.yyyyMMdd)
                .setDefaultValue("-")
                .setMaxColumnNumber(2)
                .setMaxRowNumber(5)
                .buildReader();

        List<String[]> data = reader.readAll();
        for (String[] column : data) {
            for (String cell : column) {
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println(reader.getPositionCellValue(3,3));
    }

}
