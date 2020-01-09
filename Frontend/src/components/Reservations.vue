/* eslint-disable no-console */
<template>
  <v-container grid-list-md>
    <v-layout
      row
      wrap
    >
      <v-flex
        text-center
        wrap
      >
        <h1 class="ma-5 display-4">จองห้องค้นคว้าออนไลน์</h1>
      </v-flex>
      <v-flex>
        <FullCalendar
          schedulerLicenseKey="GPL-My-Project-Is-Open-Source"
          :plugins="calendarPlugins"
          defaultView='resourceTimelineDay'
          resourceGroupField='building'
          minTime='08:00:00'
          maxTime='18:00:00'
          :aspectRatio="1.5"
          :header="{
          left: 'today prev,next',
          center: 'title',
          right: 'resourceTimelineDay,resourceTimelineThreeDay,resourceTimelineWeek,listWeekAll'
          }"
          :views="{
            resourceTimelineThreeDay: {
              type: 'resourceTimeline',
              duration: { days: 3 },
              buttonText: '3 days'
            },
            listWeekAll:{
              type:'listWeek',
              durations:{week:3},
              buttonText:'listweek'
            }
                }"
          :resourceColumns="[{
            labelText:'Room',
            field:'title'
          }]"
          :resources="resourcesRoom"
          :events="event"
        />
      </v-flex>
      <v-flex md12>
        <!-- <v-sheet class="md-lg-auto" max-width="1200"> -->
        <v-layout
          row
          wrap
        >
          <v-flex md12>
            <v-layout
              row
              wrap
            >
              <v-flex
                md3
                text-center
                wrap
              >
                <br />
                <h2 class="mt-2">ชื่อผู้ใช้บริการ</h2>
              </v-flex>
              <v-flex md7>
                <br />
                <v-select
                  outlined
                  class="mr-10"
                  :items="customers"
                  item-text="name"
                  item-value="id"
                  v-model="BookRoom.customerBook"
                  label="Customer"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-flex>
          <v-flex md12>
            <v-layout
              row
              wrap
            >
              <v-flex
                md3
                text-center
                wrap
              >
                <br />
                <h2 class="mt-2">จุดประสงค์การใช้ห้อง</h2>
              </v-flex>
              <v-flex md7>
                <br />
                <v-select
                  outlined
                  multiple
                  class="mr-10"
                  :items="type"
                  item-text="name"
                  item-value="purposeRoom_id"
                  v-model="BookRoom.typeselect"
                  label="PurposeRoom"
                ><template v-slot:prepend-item>
                    <v-list-item
                      ripple
                      @click="toggle"
                    >
                      <v-list-item-action>
                        <v-icon :color=" BookRoom.typeselect.length > 0 ? 'indigo darken-4' : '' ">{{ icon }}</v-icon>
                      </v-list-item-action>
                      <v-list-item-content>
                        <v-list-item-title>Select All</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                    <v-divider class="mt-2"></v-divider>
                  </template>
                </v-select>
              </v-flex>
            </v-layout>
          </v-flex>
          <v-flex md12>
            <v-layout
              row
              wrap
            >
              <v-flex
                md3
                text-center
                wrap
              >
                <br />
                <h2 class="mt-2">หมายเลขห้อง</h2>
              </v-flex>
              <v-flex md7>
                <br />
                <v-select
                  outlined
                  class="mr-10"
                  :items="Room"
                  item-text="room.room_name"
                  item-value="room.room_id"
                  v-model="BookRoom.Roomselect"
                  label="RoomNumber"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-flex>

          <v-flex md12>
            <v-layout
              row
              wrap
            >
              <v-flex
                md3
                text-center
                wrap
              >
                <br />
                <h2 class="mt-2">เวลาเริ่มต้น</h2>
              </v-flex>
              <v-flex md7>
                <br />
                <v-select
                  outlined
                  class="mr-10"
                  :items="StartTime"
                  item-text="time"
                  item-value="idstarttime"
                  v-model="BookRoom.starttime"
                  label="StartTime"
                ></v-select>

              </v-flex>
            </v-layout>
          </v-flex>

          <v-flex md12>

            <v-layout
              row
              wrap
            >
              <v-flex
                md3
                text-center
                wrap
              >
                <br />
                <h2 class="mt-2">เวลาสิ้นสุด</h2>
              </v-flex>
              <v-flex md7>
                <br />
                <v-select
                  outlined
                  class="mr-10"
                  :items="EndTime"
                  item-text="time"
                  item-value="idendtime"
                  v-model="BookRoom.endtimeBook"
                  label="EndTime"
                ></v-select>
                {{BookRoom}}
              </v-flex>
            </v-layout>

          </v-flex>
        </v-layout>
        <v-btn
          style="margin:0 10% 0 35%"
          x-large
          justify="center"
          outline
          color="primary"
          dark
          v-on:click="SaveRoom"
        >
          ยืนยันการจอง
          <v-icon
            large
            dark
            right
          >mdi-checkbox-marked-circle</v-icon>
        </v-btn>
        <v-btn
          x-large
          outline
          color="blue-grey"
          dark
          v-on:click="clear"
        >
          เคลียร์ข้อมูล
          <v-icon
            large
            dark
            right
          >mdi-cancel</v-icon>
        </v-btn>

        <!-- </v-sheet> -->
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
/*eslint-disable */
import FullCalendar from "@fullcalendar/vue";
import resourceTimelinePlugin from "@fullcalendar/resource-timeline";
import listPlugin from "@fullcalendar/list";
import http from "../http-common";
export default {
  components: {
    FullCalendar
  },
  data() {
    return {
      calendarPlugins: [resourceTimelinePlugin, listPlugin],
      StartTime: [
        { idstarttime: 1, time: "08:00:00" }, //
        { idstarttime: 2, time: "08:30:00" }, // "08:30:00",
        { idstarttime: 3, time: "09:00:00" }, // "09:00:00",
        { idstarttime: 4, time: "10:00:00" }, // "10:00:00",
        { idstarttime: 5, time: "10:30:00" }, // "10:30:00",
        { idstarttime: 6, time: "11:00:00" }, // "11:00:00",
        { idstarttime: 7, time: "11:30:00" }, // "11:30:00",
        { idstarttime: 8, time: "12:00:00" }, // "12:00:00",
        { idstarttime: 9, time: "12:30:00" }, // "12:30:00",
        { idstarttime: 10, time: "13:30:00" }, // "13:30:00",
        { idstarttime: 11, time: "14:30:00" }, // "14:30:00",
        { idstarttime: 12, time: "15:00:00" }, // "15:00:00",
        { idstarttime: 13, time: "15:30:00" }, // "15:30:00",
        { idstarttime: 14, time: "16:00:00" }, // "16:00:00",
        { idstarttime: 15, time: "16:30:00" }, // "16:30:00",
        { idstarttime: 16, time: "17:00:00" }, // "17:00:00",
        { idstarttime: 17, time: "17:30:00" } // "17:30:00"
      ],
      EndTime: [
        { idendtime: 2, time: "08:30:00" }, // "08:30:00",
        { idendtime: 3, time: "09:00:00" }, // "09:00:00",
        { idendtime: 4, time: "10:00:00" }, // "10:00:00",
        { idendtime: 5, time: "10:30:00" }, // "10:30:00",
        { idendtime: 6, time: "11:00:00" }, // "11:00:00",
        { idendtime: 7, time: "11:30:00" }, // "11:30:00",
        { idendtime: 8, time: "12:00:00" }, // "12:00:00",
        { idendtime: 9, time: "12:30:00" }, // "12:30:00",
        { idendtime: 10, time: "13:30:00" }, // "13:30:00",
        { idendtime: 11, time: "14:30:00" }, // "14:30:00",
        { idendtime: 12, time: "15:00:00" }, // "15:00:00",
        { idendtime: 13, time: "15:30:00" }, // "15:30:00",
        { idendtime: 14, time: "16:00:00" }, // "16:00:00",
        { idendtime: 15, time: "16:30:00" }, // "16:30:00",
        { idendtime: 16, time: "17:00:00" }, // "17:00:00",
        { idendtime: 17, time: "17:30:00" }, // "17:30:00"
        { idendtime: 18, time: "18:00:00" } //
      ],
      BookRoom: {
        typeselect: [],
        starttime: "",
        endtimeBook: "",
        Roomselect: "",
        customerBook: ""
      },
      Room: [], // ห้องที่เปิดให้จองได้
      type: [], // ข้อมูลจุดประสงค์การจองห้อง
      customers: [], //ดึงข้อมูล สมาชิก
      resourcesRoom: [], //ลงข้อมูลห้องไว้ Table
      event: [
        {
          id: "1",
          title: "TEST EVENT",
          start: "2020-01-08",
          resourceId: "i",
          color: "green",
          textColor: "yellow"
        },

        {
          id: "2",
          title: "FLEX N GATE",
          start: "2019-12-13T10:00:00",
          end: "2019-12-13T12:00:00",
          resourceId: "c"
        },
        {
          id: "3",
          title: "FLEX N GATE",
          start: "2019-12-14T10:00:00",
          end: "2019-12-14T12:00:00",
          resourceId: "a"
        }
      ]
    };
  },
  methods: {
    //กด select All ใน combobox
    toggle() {
      this.$nextTick(() => {
        if (this.likesAllPurpose) {
          this.BookRoom.typeselect = [];
        } else {
          // เคลียร์ค่าใน Array ก่อนทำการเพิ่มค่า
          this.BookRoom.typeselect = [];
          this.type.forEach((value, index) => {
            this.BookRoom.typeselect.push(this.type[index].purposeRoom_id);
          });
          // this.BookRoom.typeselect = this.type.slice();
        }
      });
    },
    SaveRoom() {
      // กดปุ่ม save
      if (this.CheckCorrectTime > 0 /*  && this.CheckCorrectTime <= 5 */) {
        http
          .post(
            "/Reservation",
            {
              customerid: this.BookRoom.customerBook,
              roomid: this.BookRoom.Roomselect,
              purosebook: this.BookRoom.typeselect,
              start: this.StartTime[this.BookRoom.starttime - 1].time,
              end: this.EndTime[this.BookRoom.endtimeBook - 2].time
            }
            // this.StartTime[this.BookRoom.starttime - 1].time +
            // "/" +
            // this.EndTime[this.BookRoom.endtimeBook - 2].time
          )
          .then(res => {
            alert("บันทึกข้อมูลการจองห้องค้นคว้าสำเร็จ");
          })
          .catch(error => {
            console.error(error);
          });
      } else {
        alert("กรุณาเลือกช่วงเวลาใหม่");
      }
    },
    clear() {
      // ลบข้อมูลออกจากcombobox
      this.BookRoom.typeselect = [];
      this.BookRoom.starttime = "";
      this.BookRoom.endtimeBook = "";
      this.BookRoom.Roomselect = "";
      this.BookRoom.customerBook = "";
    },
    getCustomers() {
      //ดึงห้องที่มีค่า null ออกมา
      http
        .get("/customer")
        .then(response => {
          this.customers = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    getTypePurposeBook() {
      //ดึงวัตถุประสงค์ของห้องมาใส่ combobox
      http
        .get("/PurposeRoom")
        .then(response => {
          this.type = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    getStatusRoom() {
      //ดึงcustomer มาใส combobox
      http
        .get("/ManageStatusD")
        .then(response => {
          this.Room = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    getManageStatus() {
      //ดึงข้อมูล room ที่ว่างทั้งหมด
    }
  },
  mounted() {
    this.getTypePurposeBook();
    this.getStatusRoom();
    this.getCustomers();
  },
  computed: {
    likesAllPurpose() {
      return this.BookRoom.typeselect.length === this.type.length;
    },
    likesSomePurpose() {
      return this.BookRoom.typeselect.length > 0 && !this.likesAllPurpose;
    },
    icon() {
      if (this.likesAllPurpose) return "mdi-close-box";
      if (this.likesSomePurpose) return "mdi-minus-box";
      return "mdi-checkbox-blank-outline";
    },
    CheckCorrectTime() {
      // let v = this.BookRoom.endtimeBook - this.BookRoom.starttime;
      return this.BookRoom.endtimeBook - this.BookRoom.starttime;
    },
    CheckcommitTime() {
      if (this.CheckCorrectTime > 0 && this.CheckCorrectTime <= 5) return true;
      else return false;
    }
  },
  watch: {
    Room() {
      this.Room.forEach((value, index) => {
        console.log(value.room.room_name);
        // console.log(value.room.room_name.indexOf("S01"));
        if (value.room.room_name.indexOf("S") == 0) {
          //เช็คว่าเจอจริงมั้ย
          console.log("เจอ S แล้ว");
          this.resourcesRoom.push({
            id: value.room.room_id,
            building: "ห้องเดี่ยว",
            title: `หมายเลขห้อง ` + value.room.room_name
          });
        } else if (value.room.room_name.indexOf("G") == 0) {
          //เช็คว่าเจอจริงมั้ย
          console.log("เจอ G แล้ว");
          this.resourcesRoom.push({
            id: value.room.room_id,
            building: "ห้องกลุ่ม",
            title: `หมายเลขห้อง ` + value.room.room_name
          });
        } else if (value.room.room_name.indexOf("P") == 0) {
          //เช็คว่าเจอจริงมั้ย
          console.log("เจอ P แล้ว");
          this.resourcesRoom.push({
            id: value.room.room_id,
            building: "ห้องประชุม",
            title: `หมายเลขห้อง ` + value.room.room_name
          });
        }
      });
    }
  }
};
/*

{ id: 'a', building: '460 Bryant', title: 'Auditorium A' },
      { id: 'b', building: '460 Bryant', title: 'Auditorium B' },
      { id: 'c', building: '460 Bryant', title: 'Auditorium C' },
      { id: 'd', building: '460 Bryant', title: 'Auditorium D' },
      { id: 'e', building: '460 Bryant', title: 'Auditorium E' },
      { id: 'f', building: '460 Bryant', title: 'Auditorium F' },
      { id: 'g', building: '564 Pacific', title: 'Auditorium G' },
      { id: 'h', building: '564 Pacific', title: 'Auditorium H' },
      { id: 'i', building: '564 Pacific', title: 'Auditorium I' },
      { id: 'j', building: '564 Pacific', title: 'Auditorium J' },
      { id: 'k', building: '564 Pacific', title: 'Auditorium K' },
      { id: 'l', building: '564 Pacific', title: 'Auditorium L' },
      { id: 'm', building: '564 Pacific', title: 'Auditorium M' },
      { id: 'n', building: '564 Pacific', title: 'Auditorium N' },
      { id: 'o', building: '101 Main St', title: 'Auditorium O' },
      { id: 'p', building: '101 Main St', title: 'Auditorium P' },
      { id: 'q', building: '101 Main St', title: 'Auditorium Q' },
      { id: 'r', building: '101 Main St', title: 'Auditorium R' },
      { id: 's', building: '101 Main St', title: 'Auditorium S' },
      { id: 't', building: '101 Main St', title: 'Auditorium T' },
      { id: 'u', building: '101 Main St', title: 'Auditorium U' },
      { id: 'v', building: '101 Main St', title: 'Auditorium V' },
      { id: 'w', building: '101 Main St', title: 'Auditorium W' },
      { id: 'x', building: '101 Main St', title: 'Auditorium X' },
      { id: 'y', building: '101 Main St', title: 'Auditorium Y' },
      { id: 'z', building: '101 Main St', title: 'Auditorium Z' }
*/
</script>

<style lang="scss" >
@import "~@fullcalendar/core/main.css";
@import "~@fullcalendar/timeline/main.css";
@import "~@fullcalendar/resource-timeline/main.css";
@import "~@fullcalendar/list/main.css";
</style>
