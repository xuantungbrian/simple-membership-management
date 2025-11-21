import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    public int getIntInput() {
        int choice = 0;
        while (choice == 0) {
            try {
                choice = reader.nextInt();
                if (choice == 0) throw new InputMismatchException();
                reader.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: INVALID INPUT. Please try again:");
            }
        }

        return choice;
    }

    public void printClubOptions() {
        System.out.println(
            "1) Club Mercury\n" +
            "2) Club Neptune\n" +
            "3) Club Jupiter\n" +
            "4) Multi Clubs"
        );
    }

    public int getChoice() {
        int choice;
        System.out.println(
            "WELCOME TO OZONE FITNESS CENTER\n" +
            "================================\n" +
            "1) Add Member\n" +
            "2) Remove Member\n" +
            "3) Display Member Information\n\n" +
            "Please select an option (or Enter -1 to quit):\n"
        );

        choice = getIntInput();
        return choice;
    }

    public String addMembers(LinkedList<Member> m) {
        String name;
        int club = 0;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal;

        System.out.print("Please enter member's name: ");
        name = reader.nextLine();

        printClubOptions();
        while (club < 1 || club > 4)
        {
            System.out.println("Please enter club ID: ");
            club = getIntInput();
        }

        if (m.size() > 0)
            memberID = m.getLast().getMemberID() + 1;
        else
            memberID = 1;

        if (club != 4) {
            cal = (n)-> {
                switch (n)
                {
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    default:
                        return -1;
                }
            };

            fees = cal.calculateFees(club);
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Single Club Member added\n");
        } else {
            cal = (n) -> {
                if (n == 4) {
                    return 1200;
                } else {
                    return -1;
                }
            };

            fees = cal.calculateFees(club);
            mbr = new SingleClubMember('M', memberID, name, fees, 100);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Multi Club Member added\n");
        }

        return mem;
    }

    public void removeMember(LinkedList<Member> m) {
        int memberID;
        System.out.println("Please enter the member ID to be removed: ");
        memberID = getIntInput();
        for (int i = 0; i<m.size();i++) {
            if (m.get(i).getMemberID() == memberID) {
                m.remove(i);
                System.out.println("Member " + memberID + "has been removed");
                return;
            }
        }
        System.out.println("Member " + memberID + "is not found");
    }

    public void printMemberInfo(LinkedList<Member> m) {
        for (int i = 0; i < m.size(); i++) {
            String[] memberInfo = m.get(i).toString().split(", ");
            if (memberInfo[0].equals("S")) {
                System.out.println(
                    "Member Type = S\n" +
                    "Member ID = " + memberInfo[1] + "\n" +
                    "Member Name = " + memberInfo[2] + "\n" +
                    "Membership Fees = " + memberInfo[3] + "\n" +
                    "Club ID = " + memberInfo[4]
                );
            } else {
                System.out.println(
                    "Member Type = S\n" +
                    "Member ID = " + memberInfo[1] + "\n" +
                    "Member Name = " + memberInfo[2] + "\n" +
                    "Membership Fees = " + memberInfo[3] + "\n" +
                    "Membership Points = " + memberInfo[4]
                );
            }
        }

    }
}
