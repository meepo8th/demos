package custom.parser.transfer.inter;

import custom.parser.transfer.exception.TransferException;

/**
 * 诊断消息转换器
 */
public interface Transfer {
    String trans(String inputString) throws TransferException;
    boolean canTrans(String inputString);
}
