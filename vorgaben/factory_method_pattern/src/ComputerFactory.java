public class ComputerFactory {
    public static Computer buildComputer(String typ, String kraft) {
        if(typ.equals("stationär")) {

            if(kraft.equals("leise schnurrend")) {
                Ram myRam = new Ram(16);
                Hdd myHdd = new Hdd(256);
                Cpu myCpu = new Cpu(8, 1.21f);
                DesktopComputer myComputer = new DesktopComputer(myRam,myHdd,myCpu);
                return myComputer;
            }

            if(kraft.equals("viel Wumms")) {
                Ram myRam = new Ram(32);
                Ssd mySsd = new Ssd(2000);
                Cpu myCpu = new Cpu(16, 4.2f);
                DesktopComputer myComputer = new DesktopComputer(myRam,mySsd,myCpu);
                return myComputer;
            }
        }

        if(typ.equals("mobil")) {

            if(kraft.equals("leise schnurrend")) {
                Ram myRam = new Ram(8);
                Hdd myHdd = new Hdd(256);
                Cpu myCpu = new Cpu(4, 1.21f);
                LaptopComputer myComputer = new LaptopComputer(myRam,myHdd,myCpu);
                return myComputer;
            }

            if(kraft.equals("viel Wumms")) {
                Ram myRam = new Ram(16);
                Ssd mySsd = new Ssd(256);
                Cpu myCpu = new Cpu(8, 2.4f);
                LaptopComputer myComputer = new LaptopComputer(myRam,mySsd,myCpu);
                return myComputer;
            }
        }

        if(typ.equals("nicht daheim")) {

            if(kraft.equals("leise schnurrend")) {
                Ram myRam = new Ram(24);
                Hdd myHdd = new Hdd(1000);
                Cpu myCpu = new Cpu(8, 1.21f);
                CloudComputer myComputer = new CloudComputer(myRam,myHdd,myCpu);
                return myComputer;
            }

            if(kraft.equals("viel Wumms")) {
                Ram myRam = new Ram(128);
                Ssd mySsd = new Ssd(10000);
                Cpu myCpu = new Cpu(42, 9.001f);
                CloudComputer myComputer = new CloudComputer(myRam,mySsd,myCpu);
                return myComputer;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Computer myDesktopComputer = buildComputer("stationär", "leise schnurrend");
        System.out.println(
            "Cores: " + myDesktopComputer.getCpu().getCores() + "\n" +
            "GHZ: " + myDesktopComputer.getCpu().getFrequency() + "\n" +
            "RAM: " + myDesktopComputer.getRam().getSize() + "\n" +
            "Drive: " + myDesktopComputer.getDrive().getStorageSize() + "\n" +
            "-------------------------------------------------");

        Computer myLaptopComputer = buildComputer("mobil", "leise schnurrend");
        System.out.println(
            "Cores: " + myLaptopComputer.getCpu().getCores() + "\n" +
            "GHZ: " + myLaptopComputer.getCpu().getFrequency() + "\n" +
            "RAM: " + myLaptopComputer.getRam().getSize() + "\n" +
            "Drive: " + myLaptopComputer.getDrive().getStorageSize() + "\n" +
            "-------------------------------------------------");

        Computer myCloudComputer = buildComputer("nicht daheim", "viel Wumms");
        System.out.println(
            "Cores: " + myCloudComputer.getCpu().getCores() + "\n" +
            "GHZ: " + myCloudComputer.getCpu().getFrequency() + "\n" +
            "RAM: " + myCloudComputer.getRam().getSize() + "\n" +
            "Drive: " + myCloudComputer.getDrive().getStorageSize() + "\n" +
            "-------------------------------------------------");
    }
}
