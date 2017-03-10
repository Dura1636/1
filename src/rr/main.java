package rr;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        Path div = Paths.get("c:\\temp");
        boolean isDir = Files.isDirectory(div);
        long size = Files.size(div);
        FileTime t = Files.getLastModifiedTime(div);
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(div)) {
            for (Path child : ds) {

            }
        }
    }

    static PathInfo scan(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            List<PathInfo> children = new ArrayList<>();
            long size = 0;
            long sumSize = 0;
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
                for (Path child : ds) {
                    PathInfo childInfo = scan(child);
                    children.add(childInfo);
                    sumSize += childInfo.size;
                }
            }
            return  new PathInfo(path,sumSize,children);
        }

        else{
                List<PathInfo> empty = Collections.emptyList();
                long size = Files.size(path);
                return new PathInfo(path, size, empty);
            }
        }
    }
