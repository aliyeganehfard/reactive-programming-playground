package com.reactive.course.sec12;

import com.reactive.course.common.Util;
import com.reactive.course.sec12.assignment.SlackMember;
import com.reactive.course.sec12.assignment.SlackRoom;

public class Lec08SlackAssignment {

    public static void main(String[] args) {

        var room = new SlackRoom("reactor");
        var sam = new SlackMember("sam");
        var jake = new SlackMember("jake");
        var mike = new SlackMember("mike");

        room.addMember(sam);
        room.addMember(jake);

        sam.says("Hi all...");
        Util.sleepSeconds(2);

        jake.says("Hey!");
        sam.says("I simply wanted to say hi...");
        Util.sleepSeconds(2);

        room.addMember(mike);
        mike.says("Hey guys..glad to be here...");
    }
}
