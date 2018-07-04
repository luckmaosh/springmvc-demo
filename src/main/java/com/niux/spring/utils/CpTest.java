package com.niux.spring.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author 矛戈 on 2017/10/16.
 */
public class CpTest {

    public static void main(String[] args) {

        try {

            int[] allRed = new int[34];//index=0空出来,放总数 ,出现次数
            int[] allBlue = new int[17];
            for (int i = 1; i < 34; i++) {
                System.out.printf("%7d \t", i);
            }
            System.out.println("");

            FileReader file = new FileReader("/Users/maso/Downloads/cp.txt");
            FileOutputStream outputStream = new FileOutputStream(new File("/Users/maso/Downloads/number2Count.txt"));
            FileOutputStream probabilityOut = new FileOutputStream(new File("/Users/maso/Downloads/number2Propability.txt"));

            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {

                //System.out.println(line);

                String[] columns = line.split("\t");
                int index = 0;
                int sum = 0;
                double[] redBalls = new double[6];
                for (String c : columns) {
                    if (index == 0) {
                        index++;
                        continue;
                    }
                    int i = Integer.parseInt(c);

                    double v = Double.parseDouble(c);

                    if (index - 1 < redBalls.length) {
                        allRed[i]++;//红球出现次数
                        redBalls[index - 1] = v;
                    } else {
                        allBlue[i]++;
                    }
                    sum += i;
                    index++;
                }

                //总数
                allRed[0] += 6;
                allBlue[0]++;

                //System.out.printf("date=%s sum=%4d variance=%6f standardDiviation=%6f %n", columns[0], sum,
                //    variance(redBalls),
                //    standardDiviation(redBalls));
                //概率
                StringBuffer propability  = new StringBuffer();
                List<TopNode> nodes = new ArrayList<TopNode>();
                for (int i = 1; i < allRed.length; i++) {
                    System.out.printf("%4f  \t", (float)allRed[i] / (float)allRed[0]);

                    propability.append((float)allRed[i] / (float)allRed[0] + "\t");
                    nodes.add(new TopNode(i, allRed[i]));

                }
                probabilityOut.write(propability.append("\n").toString().getBytes());

                //top6 排序
                Collections.sort(nodes, new Comparator<TopNode>() {
                    @Override
                    public int compare(TopNode o1, TopNode o2) {
                        if (o1.getCount() < o2.getCount()) {
                            return 1;
                        } else if (o1.getCount() == o2.getCount()) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                });

                //int[] tmp = new int[allRed.length - 1];
                //System.arraycopy(allRed, 1, tmp, 0, tmp.length);
                int topk = 33;
                //int[] topKByHeap = new TopK().getTopKByHeap(tmp, topk
                //);
                System.out.print("--->topk= |");
                StringBuffer topkLine = new StringBuffer();
                for (int i = 0; i < topk; i++) {
                    //System.out.printf("%4d  \t", topKByHeap[i]);
                    System.out.printf("%4d=%2d", nodes.get(i).getNumber(), nodes.get(i).getCount());

                    topkLine.append(nodes.get(i).getNumber() + "=" + nodes.get(i).getCount() + "\t");
                    if ((i + 1) % 5 == 0) {
                        System.out.print(" | ");
                    }
                }

                //写入文件
                outputStream.write(topkLine.append("\n").toString().getBytes());
                System.out.println("  | <--topk    ");
            }

            outputStream.flush();
            outputStream.close();

            probabilityOut.flush();
            probabilityOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //方差s^2=[(x1-x)^2 +...(xn-x)^2]/n
    private static double variance(double[] x) {
        int m = x.length;
        double sum = 0;
        for (double aX : x) {//求和
            sum += aX;
        }
        double dAve = sum / m;//求平均值
        double dVar = 0;
        for (double aX : x) {//求方差
            dVar += (aX - dAve) * (aX - dAve);
        }
        return dVar / m;
    }

    //标准差σ=sqrt(s^2)
    public static double standardDiviation(double[] x) {
        int m = x.length;
        double sum = 0;
        for (int i = 0; i < m; i++) {//求和
            sum += x[i];
        }
        double dAve = sum / m;//求平均值
        double dVar = 0;
        for (int i = 0; i < m; i++) {//求方差
            dVar += (x[i] - dAve) * (x[i] - dAve);
        }
        return Math.sqrt(dVar / m);
    }

}
