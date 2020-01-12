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
          defaultView="resourceTimelineDay"
          resourceGroupField="building"
          minTime="08:00:00"
          maxTime="18:00:00"
          eventTextColor="white"
          :aspectRatio="1.5"
          :header="{
            left: 'today prev,next',
            center: 'title',
            right:
              'resourceTimelineDay,resourceTimelineThreeDay,resourceTimelineWeek,listWeekAll'
          }"
          :views="{
            resourceTimelineThreeDay: {
              type: 'resourceTimeline',
              duration: { days: 3 },
              buttonText: '3 days'
            },
            listWeekAll: {
              type: 'listWeek',
              durations: { week: 3 },
              buttonText: 'listweek'
            }
          }"
          :resourceColumns="[
            {
              labelText: 'Room',
              field: 'title'
            }
          ]"
          :resources="resourcesRoom"
          :events="event"
        />
      </v-flex>
      <v-flex md12>
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
                  v-if="lock"
                  outlined
                  :readonly="true"
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
                        <v-icon :color="
                            BookRoom.typeselect.length > 0
                              ? 'indigo darken-4'
                              : ''
                          ">{{ icon }}</v-icon>
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
                  item-value="manageStatus_id"
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
                  item-text="timestart"
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
                  item-text="timeend"
                  item-value="idendtime"
                  v-model="BookRoom.endtimeBook"
                  label="EndTime"
                ></v-select>
              </v-flex>
            </v-layout>
          </v-flex>
        </v-layout>
        <v-btn
          style="margin:0 5% 0 25%"
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
          style="margin:0 2% 0 2%"
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
        <v-btn
          style="margin:0 0 0 5%"
          x-large
          outline
          color="red"
          dark
          v-on:click="back"
        >
          ออกจากระบบ
          <v-icon
            large
            dark
            right
          >mdi-arrow-left</v-icon>
        </v-btn>

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
import { formatDate } from "@fullcalendar/core";
import { LocalDate, LocalDateTime, LocalTime } from "js-joda";
export default {
  components: {
    FullCalendar
  },
  data() {
    return {
      calendarPlugins: [resourceTimelinePlugin, listPlugin],
      StartTime: [
        { idstarttime: 1, timestart: "08:00:00" }, //
        { idstarttime: 2, timestart: "08:30:00" }, // "08:30:00",
        { idstarttime: 3, timestart: "09:00:00" }, // "09:00:00",
        { idstarttime: 4, timestart: "10:00:00" }, // "10:00:00",
        { idstarttime: 5, timestart: "10:30:00" }, // "10:30:00",
        { idstarttime: 6, timestart: "11:00:00" }, // "11:00:00",
        { idstarttime: 7, timestart: "11:30:00" }, // "11:30:00",
        { idstarttime: 8, timestart: "12:00:00" }, // "12:00:00",
        { idstarttime: 9, timestart: "12:30:00" }, // "12:30:00",
        { idstarttime: 10, timestart: "13:30:00" }, // "13:30:00",
        { idstarttime: 11, timestart: "14:30:00" }, // "14:30:00",
        { idstarttime: 12, timestart: "15:00:00" }, // "15:00:00",
        { idstarttime: 13, timestart: "15:30:00" }, // "15:30:00",
        { idstarttime: 14, timestart: "16:00:00" }, // "16:00:00",
        { idstarttime: 15, timestart: "16:30:00" }, // "16:30:00",
        { idstarttime: 16, timestart: "17:00:00" }, // "17:00:00",
        { idstarttime: 17, timestart: "17:30:00" } // "17:30:00"
      ],
      EndTime: [
        { idendtime: 2, timeend: "08:30:00" }, // "08:30:00",
        { idendtime: 3, timeend: "09:00:00" }, // "09:00:00",
        { idendtime: 4, timeend: "10:00:00" }, // "10:00:00",
        { idendtime: 5, timeend: "10:30:00" }, // "10:30:00",
        { idendtime: 6, timeend: "11:00:00" }, // "11:00:00",
        { idendtime: 7, timeend: "11:30:00" }, // "11:30:00",
        { idendtime: 8, timeend: "12:00:00" }, // "12:00:00",
        { idendtime: 9, timeend: "12:30:00" }, // "12:30:00",
        { idendtime: 10, timeend: "13:30:00" }, // "13:30:00",
        { idendtime: 11, timeend: "14:30:00" }, // "14:30:00",
        { idendtime: 12, timeend: "15:00:00" }, // "15:00:00",
        { idendtime: 13, timeend: "15:30:00" }, // "15:30:00",
        { idendtime: 14, timeend: "16:00:00" }, // "16:00:00",
        { idendtime: 15, timeend: "16:30:00" }, // "16:30:00",
        { idendtime: 16, timeend: "17:00:00" }, // "17:00:00",
        { idendtime: 17, timeend: "17:30:00" }, // "17:30:00"
        { idendtime: 18, timeend: "18:00:00" } //
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
      resvertion: [], //ดึงข้อมูลการจองม
      lock: false,
      cusid: "",
      // event: [
      //   {
      //     id: "1",
      //     title: "TEST EVENT",
      //     start: "2020-01-08",
      //     resourceId: "i",
      //     color: "green",
      //     textColor: "yellow"
      //   },

      //   {
      //     id: "2",
      //     title: "FLEX N GATE",
      //     start: "2019-12-13T10:00:00",
      //     end: "2019-12-13T12:00:00",
      //     resourceId: "c"
      //   },
      //   {
      //     id: "3",
      //     title: "FLEX N GATE",
      //     start: "2019-12-14T10:00:00",
      //     end: "2019-12-14T12:00:00",
      //     resourceId: "a"
      //   }
      // ]
      event: []
    };
  },
  methods: {
    lockemployee() {
      this.cusid = this.$route.params.cus;
      this.BookRoom.customerBook = this.cusid;
      this.lock = true;
    },
    back() {
      this.$router.push("/");
    },
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
      if (
        this.CheckCorrectTime > 0 /*  && this.CheckCorrectTime <= 5 */ &&
        this.BookRoom.customerBook != "" &&
        this.BookRoom.Roomselect != "" &&
        this.BookRoom.typeselect != "" &&
        this.BookRoom.starttime != "" &&
        this.BookRoom.endtimeBook != ""
        // this.MustNotBeRepeat()
      ) {
        http
          .post("/Reservation", {
            customerid: this.BookRoom.customerBook,
            roomid: this.BookRoom.Roomselect,
            purosebook: this.BookRoom.typeselect,
            start: this.StartTime[this.BookRoom.starttime - 1].timestart,
            end: this.EndTime[this.BookRoom.endtimeBook - 2].timeend
          })
          .then(res => {
            alert("บันทึกข้อมูลการจองห้องค้นคว้าสำเร็จ");
            this.clear();
          })
          .catch(error => {
            console.error(error);
          });
      } else {
        alert("โปรดตรวจสอบข้อมูลของท่านใหม่");
        this.clear();
      }
    },

    clear() {
      // ลบข้อมูลออกจากcombobox
      this.BookRoom.typeselect = [];
      this.BookRoom.starttime = "";
      this.BookRoom.endtimeBook = "";
      this.BookRoom.Roomselect = "";
      // this.BookRoom.customerBook = "";
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

    getResvertionRoom() {
      http
        .get("/reservationses")
        .then(response => {
          this.resvertion = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getTypePurposeBook();
    this.getStatusRoom();
    this.getCustomers();
    this.getResvertionRoom();
    this.lockemployee();
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
      this.resourcesRoom = [];
      this.Room.forEach((value, index) => {
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
    },
    resvertion() {
      let i = 1;
      this.resvertion.forEach(value => {
        // console.log(value.endTime);
        this.event.push({
          id: ++i,
          title: value.customer.name,
          start: value.startTime,
          end: value.endTime,
          resourceId: value.manageStatus.room.room_id
          // textColor: "black"
        });
      });
    }
  }
};
</script>

<style lang="scss">
@import "~@fullcalendar/core/main.css";
@import "~@fullcalendar/timeline/main.css";
@import "~@fullcalendar/resource-timeline/main.css";
@import "~@fullcalendar/list/main.css";
</style>
