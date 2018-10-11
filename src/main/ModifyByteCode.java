

import org.gjt.jclasslib.io.ClassFileWriter;
import org.gjt.jclasslib.structures.CPInfo;
import org.gjt.jclasslib.structures.ClassFile;
import org.gjt.jclasslib.structures.constants.ConstantUtf8Info;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class ModifyByteCode {
    public void Run() {
        try {
            String filePath = "G:\\tmp\\com\\bixolon\\printer\\connectivity\\ConnectivityService.class";
            FileInputStream fis = new FileInputStream(filePath);

            DataInput di = new DataInputStream(fis);
            ClassFile cf = new ClassFile();
            cf.read(di);
            CPInfo[] infos = cf.getConstantPool();

            int pos = 89;
            if (infos[pos] != null) {
                ConstantUtf8Info uInfo = (ConstantUtf8Info) infos[pos];
                uInfo.setString("设备连接断开");
                infos[pos] = uInfo;
            }
            cf.setConstantPool(infos);

            pos = 96;
            if (infos[pos] != null) {
                ConstantUtf8Info uInfo = (ConstantUtf8Info) infos[pos];
                uInfo.setString("连接设备失败");
                infos[pos] = uInfo;
            }
            cf.setConstantPool(infos);

            fis.close();
            File f = new File(filePath);
            ClassFileWriter.writeToFile(f, cf);

        } catch (Exception e) {

        }
    }

    public static void main(String args[]) {
        new ModifyByteCode().Run();
    }
}