package fun.neverth;

import java.util.ArrayList;
import java.util.Random;

public class Part7 {
    public static void main(String[] args) {
        String a = "null,null,18.00000,null,134.50000,null,158.00000,null,158.00000,null,158.00000,null,169.00000,null,158.00000,null,169.00000,null,180.00000,null,210.00000,null,240.00000,null,245.50000,null,251.00000,null,283.50000,null,316.00000,null,283.50000,null,316.00000,null,333.00000,null,350.00000,null,354.50000,null,350.00000,null,354.50000,null,350.00000,null,354.50000,null,350.00000,null,333.00000,null,350.00000,null,333.00000,null,316.00000,null,293.50000,null,271.00000,null,261.00000,null,271.00000,null,261.00000,null,271.00000,null,293.50000,null,271.00000,null,271.00000,null,271.00000,null,261.00000,null,251.00000,null,261.00000,null,251.00000,null,245.50000,null,240.00000,null,245.50000,null,251.00000,null,261.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,270.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,270.00000,null,269.00000,null,270.00000,null,271.00000,null,271.00000,null,271.00000,null,270.00000,null,269.00000,null,270.00000,null,269.00000,null,265.00000,null,269.00000,null,270.00000,null,269.00000,null,265.00000,null,269.00000,null,270.00000,null,271.00000,null,271.00000,null,271.00000,null,270.00000,null,269.00000,null,265.00000,null,261.00000,null,265.00000,null,261.00000,null,265.00000,null,261.00000,null,265.00000,null,269.00000,null,265.00000,null,261.00000,null,265.00000,null,269.00000,null,270.00000,null,271.00000,null,270.00000,null,271.00000,null,271.00000,null,271.00000,null,272.50000,null,274.00000,null,272.50000,null,274.00000,null,272.50000,null,271.00000,null,272.50000,null,274.00000,null,272.50000,null,274.00000,null,275.00000,null,276.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,277.50000,null,277.00000,null,276.50000,null,277.00000,null,277.50000,null,278.00000,null,277.50000,null,277.00000,null,276.50000,null,277.00000,null,277.50000,null,277.00000,null,276.50000,null,276.00000,null,276.50000,null,276.00000,null,276.50000,null,276.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,277.50000,null,277.00000,null,276.50000,null,277.00000,null,276.50000,null,276.00000,null,276.50000,null,277.00000,null,276.50000,null,276.00000,null,276.50000,null,277.00000,null,277.50000,null,277.00000,null,276.50000,null,276.00000,null,275.00000,null,274.00000,null,275.00000,null,274.00000,null,275.00000,null,274.00000,null,275.00000,null,276.00000,null,276.50000,null,276.00000,null,275.00000,null,276.00000,null,275.00000,null,274.00000,null,275.00000,null,274.00000,null,273.50000,null,274.00000,null,273.50000,null,274.00000,null,273.50000,null,274.00000,null,275.00000,null,276.00000,null,276.50000,null,277.00000,null,277.50000,null,277.00000,null,276.50000,null,276.00000,null,276.50000,null,277.00000,null,276.50000,null,276.00000,null,276.50000,null,276.00000,null,276.50000,null,277.00000,null,277.50000,null,278.00000,null,277.50000,null,277.00000,null,277.50000,null,278.00000,null,277.50000,null,278.00000,null,277.50000,null,278.00000,null,280.50000,null,278.00000,null,277.50000,null,277.00000,null,276.50000,null,277.00000,null,277.50000,null,278.00000,null,280.50000,null,283.00000,null,283.00000,null,283.00000,null,285.00000,null,287.00000,null,285.00000,null,283.00000,null,283.00000,null,283.00000,null,280.50000,null,283.00000,null,280.50000,null,278.00000,null,280.50000,null,283.00000,null,280.50000,null,283.00000,null,283.00000,null,283.00000,null,285.00000,null,287.00000,null,288.50000,null,290.00000,null,292.00000,null,290.00000,null,288.50000,null,287.00000,null,285.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,285.00000,null,283.00000,null,285.00000,null,283.00000,null,285.00000,null,287.00000,null,288.50000,null,290.00000,null,289.50000,null,290.00000,null,290.50000,null,290.00000,null,289.50000,null,289.00000,null,288.00000,null,287.00000,null,288.00000,null,289.00000,null,289.50000,null,290.00000,null,289.50000,null,290.00000,null,289.50000,null,289.00000,null,288.00000,null,287.00000,null,288.00000,null,287.00000,null,288.00000,null,289.00000,null,289.50000,null,290.00000,null,289.50000,null,289.00000,null,289.50000,null,289.00000,null,289.50000,null,290.00000,null,290.50000,null,290.00000,null,290.50000,null,290.00000,null,290.50000,null,291.00000,null,292.50000,null,294.00000,null,295.50000,null,297.00000,null,295.50000,null,297.00000,null,295.50000,null,297.00000,null,295.50000,null,294.00000,null,295.50000,null,294.00000,null,292.50000,null,294.00000,null,292.50000,null,294.00000,null,295.50000,null,294.00000,null,292.50000,null,294.00000,null,292.50000,null,294.00000,null,295.50000,null,297.00000,null,295.50000,null,297.00000,null,295.50000,null,297.00000,null,301.00000,null,297.00000,null,295.50000,null,294.00000,null,295.00000,null,294.00000,null,295.00000,null,294.00000,null,295.00000,null,296.00000,null,296.50000,null,296.00000,null,296.50000,null,297.00000,null,298.00000,null,297.00000,null,298.00000,null,299.00000,null,299.50000,null,300.00000,null,302.50000,null,300.00000,null,299.50000,null,299.00000,null,298.00000,null,299.00000,null,298.00000,null,299.00000,null,298.00000,null,297.00000,null,298.00000,null,297.00000,null,297.00000,null,297.00000,null,298.00000,null,299.00000,null,299.50000,null,299.00000,null,299.50000,null,299.00000,null,298.00000,null,297.00000,null,297.00000,null,297.00000,null,297.00000,null,297.00000,null,298.00000,null,299.00000,null,299.50000,null,299.00000,null,298.00000,null,297.00000,null,298.00000,null,299.00000,null,298.00000,null,297.00000,null,297.00000,null,297.00000,null,296.50000,null,296.00000,null,295.00000,null,296.00000,null,295.00000,null,294.00000,null,292.50000,null,291.00000,null,292.50000,null,294.00000,null,295.00000,null,294.00000,null,295.00000,null,294.00000,null,295.00000,null,294.00000,null,292.50000,null,291.00000,null,292.50000,null,291.00000,null,292.50000,null,294.00000,null,292.50000,null,294.00000,null,292.50000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,292.50000,null,294.00000,null,295.00000,null,296.00000,null,296.50000,null,297.00000,null,296.50000,null,296.00000,null,296.50000,null,297.00000,null,296.50000,null,296.00000,null,296.50000,null,297.00000,null,297.00000,null,297.00000,null,298.00000,null,297.00000,null,298.00000,null,299.00000,null,299.50000,null,300.00000,null,299.50000,null,299.00000,null,299.50000,null,300.00000,null,300.50000,null,300.00000,null,300.50000,null,300.00000,null,300.50000,null,301.00000,null,300.50000,null,300.00000,null,300.50000,null,301.00000,null,303.00000,null,305.00000,null,303.00000,null,305.00000,null,305.50000,null,306.00000,null,306.50000,null,307.00000,null,308.00000,null,307.00000,null,308.00000,null,309.00000,null,308.00000,null,309.00000,null,307.50000,null,306.00000,null,307.50000,null,306.00000,null,306.50000,null,306.00000,null,306.50000,null,307.00000,null,306.50000,null,307.00000,null,308.00000,null,307.00000,null,308.00000,null,309.00000,null,309.50000,null,309.00000,null,308.00000,null,309.00000,null,309.50000,null,309.00000,null,309.50000,null,310.00000,null,309.50000,null,310.00000,null,310.00000,null,310.00000,null,309.50000,null,310.00000,null,310.00000,null,310.00000,null,310.00000,null,310.00000,null,311.50000,null,310.00000,null,310.00000,null,310.00000,null,311.50000,null,313.00000,null,312.50000,null,312.00000,null,311.00000,null,312.00000,null,312.50000,null,312.00000,null,312.50000,null,312.00000,null,312.50000,null,313.00000,null,312.50000,null,312.00000,null,311.00000,null,310.00000,null,310.00000,null,310.00000,null,311.00000,null,312.00000,null,312.50000,null,313.00000,null,312.50000,null,313.00000,null,312.50000,null,313.00000,null,313.50000,null,313.00000,null,313.50000,null,314.00000,null,315.00000,null,316.00000,null,316.50000,null,317.00000,null,316.50000,null,316.00000,null,315.00000,null,316.00000,null,315.00000,null,314.00000,null,315.00000,null,314.00000,null,315.00000,null,314.00000,null,315.00000,null,314.00000,null,315.00000,null,316.00000,null,316.50000,null,316.00000,null,316.50000,null,317.00000,null,316.50000,null,316.00000,null,315.00000,null,316.00000,null,316.50000,null,316.00000,null,315.00000,null,316.00000,null,316.50000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,316.50000,null,316.00000,null,316.50000,null,316.00000,null,316.50000,null,316.00000,null,315.00000,null,316.00000,null,316.50000,null,316.00000,null,316.50000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.50000,null,318.00000,null,318.00000,null,318.00000,null,317.50000,null,318.00000,null,318.00000,null,318.00000,null,319.50000,null,318.00000,null,319.50000,null,318.00000,null,319.50000,null,321.00000,null,319.50000,null,318.00000,null,319.50000,null,318.00000,null,318.00000,null,318.00000,null,318.00000,null,318.00000,null,318.00000,null,318.00000,null,317.50000,null,318.00000,null,317.50000,null,317.00000";
        String[] split = a.split(",");

        String b = "null,null,18.00000,null,134.50000,null,158.00000,null,158.00000,null,158.00000,null,169.00000,null,158.00000,null,169.00000,null,180.00000,null,210.00000,null,240.00000,null,245.50000,null,251.00000,null,283.50000,null,316.00000,null,283.50000,null,316.00000,null,333.00000,null,350.00000,null,354.50000,null,350.00000,null,354.50000,null,350.00000,null,354.50000,null,350.00000,null,333.00000,null,350.00000,null,333.00000,null,316.00000,null,293.50000,null,271.00000,null,261.00000,null,271.00000,null,261.00000,null,271.00000,null,293.50000,null,271.00000,null,271.00000,null,271.00000,null,261.00000,null,251.00000,null,261.00000,null,251.00000,null,245.50000,null,240.00000,null,245.50000,null,251.00000,null,261.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,270.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,271.00000,null,270.00000,null,269.00000,null,270.00000,null,271.00000,null,271.00000,null,271.00000,null,270.00000,null,269.00000,null,270.00000,null,269.00000,null,265.00000,null,269.00000,null,270.00000,null,269.00000,null,265.00000,null,269.00000,null,270.00000,null,271.00000,null,271.00000,null,271.00000,null,270.00000,null,269.00000,null,265.00000,null,261.00000,null,265.00000,null,261.00000,null,265.00000,null,261.00000,null,265.00000,null,269.00000,null,265.00000,null,261.00000,null,265.00000,null,269.00000,null,270.00000,null,271.00000,null,270.00000,null,271.00000,null,271.00000,null,271.00000,null,272.50000,null,274.00000,null,272.50000,null,274.00000,null,272.50000,null,271.00000,null,272.50000,null,274.00000,null,272.50000,null,274.00000,null,275.00000,null,276.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,277.50000,null,277.00000,null,276.50000,null,277.00000,null,277.50000,null,278.00000,null,277.50000,null,277.00000,null,276.50000,null,277.00000,null,277.50000,null,277.00000,null,276.50000,null,276.00000,null,276.50000,null,276.00000,null,276.50000,null,276.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,276.50000,null,277.00000,null,277.50000,null,277.00000,null,276.50000,null,277.00000,null,276.50000,null,276.00000,null,276.50000,null,277.00000,null,276.50000,null,276.00000,null,276.50000,null,277.00000,null,277.50000,null,277.00000,null,276.50000,null,276.00000,null,275.00000,null,274.00000,null,275.00000,null,274.00000,null,275.00000,null,274.00000,null,275.00000,null,276.00000,null,276.50000,null,276.00000,null,275.00000,null,276.00000,null,275.00000,null,274.00000,null,275.00000,null,274.00000,null,273.50000,null,274.00000,null,273.50000,null,274.00000,null,273.50000,null,274.00000,null,275.00000,null,276.00000,null,276.50000,null,277.00000,null,277.50000,null,277.00000,null,276.50000,null,276.00000,null,276.50000,null,277.00000,null,276.50000,null,276.00000,null,276.50000,null,276.00000,null,276.50000,null,277.00000,null,277.50000,null,278.00000,null,277.50000,null,277.00000,null,277.50000,null,278.00000,null,277.50000,null,278.00000,null,277.50000,null,278.00000,null,280.50000,null,278.00000,null,277.50000,null,277.00000,null,276.50000,null,277.00000,null,277.50000,null,278.00000,null,280.50000,null,283.00000,null,283.00000,null,283.00000,null,285.00000,null,287.00000,null,285.00000,null,283.00000,null,283.00000,null,283.00000,null,280.50000,null,283.00000,null,280.50000,null,278.00000,null,280.50000,null,283.00000,null,280.50000,null,283.00000,null,283.00000,null,283.00000,null,285.00000,null,287.00000,null,287.00000,null,287.00000,null,288.50000,null,287.00000,null,287.00000,null,287.00000,null,285.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,283.00000,null,285.00000,null,283.00000,null,285.00000,null,283.00000,null,285.00000,null,287.00000,null,287.00000,null,287.00000,null,288.00000,null,289.00000,null,289.50000,null,289.00000,null,288.00000,null,287.00000,null,287.00000,null,287.00000,null,287.00000,null,287.00000,null,288.00000,null,289.00000,null,288.00000,null,289.00000,null,288.00000,null,287.00000,null,287.00000,null,287.00000,null,287.00000,null,287.00000,null,287.00000,null,287.00000,null,288.00000,null,289.00000,null,288.00000,null,287.00000,null,288.00000,null,287.00000,null,288.00000,null,289.00000,null,289.50000,null,289.00000,null,289.50000,null,289.00000,null,289.50000,null,290.00000,null,290.50000,null,291.00000,null,292.50000,null,294.00000,null,292.50000,null,294.00000,null,295.50000,null,297.00000,null,295.50000,null,294.00000,null,295.50000,null,294.00000,null,292.50000,null,294.00000,null,292.50000,null,294.00000,null,295.50000,null,294.00000,null,292.50000,null,294.00000,null,292.50000,null,294.00000,null,295.50000,null,297.00000,null,295.50000,null,297.00000,null,295.50000,null,297.00000,null,298.00000,null,297.00000,null,295.50000,null,294.00000,null,295.00000,null,294.00000,null,295.00000,null,294.00000,null,295.00000,null,296.00000,null,296.50000,null,296.00000,null,296.50000,null,297.00000,null,298.00000,null,297.00000,null,298.00000,null,299.00000,null,299.50000,null,300.00000,null,302.50000,null,300.00000,null,299.50000,null,299.00000,null,298.00000,null,299.00000,null,298.00000,null,299.00000,null,298.00000,null,297.00000,null,298.00000,null,297.00000,null,297.00000,null,297.00000,null,298.00000,null,299.00000,null,299.50000,null,299.00000,null,299.50000,null,299.00000,null,298.00000,null,297.00000,null,297.00000,null,297.00000,null,297.00000,null,297.00000,null,298.00000,null,299.00000,null,299.50000,null,299.00000,null,298.00000,null,297.00000,null,298.00000,null,299.00000,null,298.00000,null,297.00000,null,297.00000,null,297.00000,null,296.50000,null,296.00000,null,295.00000,null,296.00000,null,295.00000,null,294.00000,null,292.50000,null,291.00000,null,292.50000,null,294.00000,null,295.00000,null,294.00000,null,295.00000,null,294.00000,null,295.00000,null,294.00000,null,292.50000,null,291.00000,null,292.50000,null,291.00000,null,292.50000,null,294.00000,null,292.50000,null,294.00000,null,292.50000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,291.00000,null,292.50000,null,294.00000,null,295.00000,null,296.00000,null,296.50000,null,297.00000,null,296.50000,null,296.00000,null,296.50000,null,297.00000,null,296.50000,null,296.00000,null,296.50000,null,297.00000,null,297.00000,null,297.00000,null,298.00000,null,297.00000,null,298.00000,null,299.00000,null,299.50000,null,300.00000,null,299.50000,null,299.00000,null,299.50000,null,300.00000,null,300.50000,null,300.00000,null,300.50000,null,300.00000,null,300.50000,null,301.00000,null,300.50000,null,300.00000,null,300.50000,null,301.00000,null,303.00000,null,305.00000,null,303.00000,null,305.00000,null,305.50000,null,306.00000,null,306.00000,null,306.00000,null,306.50000,null,306.00000,null,306.50000,null,307.00000,null,306.50000,null,307.00000,null,308.00000,null,309.00000,null,308.00000,null,307.00000,null,306.50000,null,306.00000,null,306.50000,null,307.00000,null,306.50000,null,307.00000,null,308.00000,null,307.00000,null,308.00000,null,309.00000,null,309.50000,null,309.00000,null,308.00000,null,309.00000,null,309.50000,null,309.00000,null,309.50000,null,310.00000,null,309.50000,null,310.00000,null,310.00000,null,310.00000,null,309.50000,null,310.00000,null,310.00000,null,310.00000,null,310.00000,null,310.00000,null,311.50000,null,310.00000,null,310.00000,null,310.00000,null,311.50000,null,313.00000,null,312.50000,null,312.00000,null,311.00000,null,312.00000,null,312.50000,null,312.00000,null,312.50000,null,312.00000,null,312.50000,null,313.00000,null,312.50000,null,312.00000,null,311.00000,null,310.00000,null,310.00000,null,310.00000,null,311.00000,null,312.00000,null,312.50000,null,313.00000,null,312.50000,null,313.00000,null,312.50000,null,313.00000,null,313.50000,null,313.00000,null,313.50000,null,314.00000,null,315.00000,null,316.00000,null,316.50000,null,317.00000,null,316.50000,null,316.00000,null,315.00000,null,316.00000,null,315.00000,null,314.00000,null,315.00000,null,314.00000,null,315.00000,null,314.00000,null,315.00000,null,314.00000,null,315.00000,null,316.00000,null,316.50000,null,316.00000,null,316.50000,null,317.00000,null,316.50000,null,316.00000,null,315.00000,null,316.00000,null,316.50000,null,316.00000,null,315.00000,null,316.00000,null,316.50000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,316.50000,null,316.00000,null,316.50000,null,316.00000,null,316.50000,null,316.00000,null,315.00000,null,316.00000,null,316.50000,null,316.00000,null,316.50000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.00000,null,317.50000,null,318.00000,null,318.00000,null,318.00000,null,317.50000,null,318.00000,null,318.00000,null,318.00000,null,318.50000,null,318.00000,null,318.50000,null,318.00000,null,318.50000,null,319.00000,null,318.50000,null,318.00000,null,318.50000,null,318.00000,null,318.00000,null,318.00000,null,318.00000,null,318.00000,null,318.00000,null,318.00000,null,317.50000,null,318.00000,null,317.50000,null,317.00000";
        String[] split1 = a.split(",");

        for (int i = 0; i < split1.length; i++) {
            if (!split[i].equals(split1[i])){
                System.out.println(111);
            }

        }


        MedianFinder medianFinder = new MedianFinder();
        for (int i = 1; i < 11; i++) {
            System.out.println(medianFinder.addNum(i));
            System.out.println(medianFinder.findMedian());
        }
    }


    static class MedianFinder {
        private Heap a, b;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            a = new Heap(true);
            b = new Heap(false);
        }

        public Integer addNum(int num) {
            if (a.size() != b.size()) {
                a.add(num);
                b.add(a.removeRoot());
            } else {
                b.add(num);
                a.add(b.removeRoot());
            }
            return null;
        }

        public double findMedian() {
            if (a.size() != b.size()) {
                return a.peek();
            } else {
                return (a.peek() + b.peek()) / 2.0;
            }
        }

        static class Heap {
            final private ArrayList<Integer> data;
            private boolean reversed;

            public Heap(boolean reversed) {
                data = new ArrayList<>();
                this.reversed = reversed;
            }

            public int size() {
                return data.size();
            }

            public void add(int v) {
                data.add(v);
                heapify(data.size() - 1);
            }

            public Integer removeRoot() {
                if (data.size() > 0) {
                    int val = data.remove(data.size() - 1);
                    heapify(data.size() - 1);
                    return val;
                }
                return null;
            }

            public Integer peek() {
                if (data.size() != 0) {
                    return data.get(data.size() - 1);
                }
                return null;
            }

            private int computeLeft(int i){
                int j = data.size() - 1 - i;
                int l = (j + 1) * 2 - 1;
                return data.size() - 1 - l;
            }

            private int computeRight(int i){
                int j = data.size() - 1 - i;
                int l = (j + 1) * 2;
                return data.size() - 1 - l;
            }

            private void heapify(int i) {
                int l = computeLeft(i);
                int r = computeRight(i);
                int item = i;
                if (!reversed) {
                    if (l >= 0 && data.get(l) > data.get(item)) {
                        item = l;
                    }
                    if (r >= 0 && data.get(r) > data.get(item)) {
                        item = r;
                    }
                } else {
                    if (l >= 0 && data.get(l) < data.get(item)) {
                        item = l;
                    }
                    if (r >= 0 && data.get(r) < data.get(item)) {
                        item = r;
                    }
                }
                if (item == i) {
                    return;
                }
                swap(i, item);
                heapify(item);
            }

            private void swap(int i, int j) {
                int tmp = data.get(i);
                data.set(i, data.get(j));
                data.set(j, tmp);
            }
        }
    }
}
