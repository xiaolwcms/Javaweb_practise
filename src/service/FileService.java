package service;

public interface FileService {
    //将数据保存到文件中
    void preservationToFile();
    //将数据按照不同年份保存到对应的目录中
    void preservationToFileDistinguishYear();
}
