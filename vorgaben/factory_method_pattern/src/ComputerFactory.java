public class ComputerFactory {
    public static Computer buildComputer(String power, String location) {
        DesktopComputer desktopComputer;
        LaptopComputer laptopComputer;
        CloudComputer cloudComputer;
        Cpu cpu;
        Hdd hdd;
        Ram ram;
        Ssd ssd;
        if(power.equals("leise schnurrend")){
            if(location.equals("stationär")){
                cpu = new Cpu(8,1.21f);
                hdd = new Hdd(256);
                ram = new Ram(16);
                desktopComputer = new DesktopComputer(ram,hdd,cpu);
                return desktopComputer;
            }
            if(location.equals("mobil")){
                cpu = new Cpu(4,1.21f);
                hdd = new Hdd(256);
                ram = new Ram(8);
                laptopComputer = new LaptopComputer(ram,hdd,cpu);
                return laptopComputer;
            }
            if(location.equals("nicht daheim")){
                cpu = new Cpu(8,1.21f);
                hdd = new Hdd(1000);
                ram = new Ram(24);
                cloudComputer = new CloudComputer(ram,hdd,cpu);
                return cloudComputer;
            }
        }

        if(power.equals("viel Wumms")){
            if(location.equals("stationär")){
                cpu = new Cpu(16,4.2f);
                ssd = new Ssd(2000);
                ram = new Ram(16);
                desktopComputer = new DesktopComputer(ram,ssd,cpu);
                return desktopComputer;
            }
            if(location.equals("mobil")){
                cpu = new Cpu(8,2.4f);
                ssd = new Ssd(256);
                ram = new Ram(8);
                laptopComputer = new LaptopComputer(ram,ssd,cpu);
                return laptopComputer;
            }
            if(location.equals("nicht daheim")){
                cpu = new Cpu(42,9.001f);
                ssd = new Ssd(10000);
                ram = new Ram(24);
                cloudComputer = new CloudComputer(ram,ssd,cpu);
                return cloudComputer;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Computer bsp = ComputerFactory.buildComputer("viel Wumms","mobil");
        System.out.println("Der passend konfigurierte Computer ist ein "+bsp.getClass().getName()+" und hat die Komponenten:");
        System.out.println("Ram: "+bsp.getRam().getSize()+"GB\n"+"Festplatte: "+bsp.getDrive().getStorageSize()+"GB "+bsp.getDrive().getClass().getName()+"\n"+
            "CPU: "+bsp.getCpu().getCores()+" Cores, "+bsp.getCpu().getFrequency()+"GHZ.");
    }
}
