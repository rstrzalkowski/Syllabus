import {Level} from "./level";
import {User} from "./user";

export interface ClassPage {
  content: Class[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Class {
  id: number;
  createdAt: Date;
  updatedAt: Date;
  level: Level;
  name: string;
  fullName: string;
  supervisingTeacher: User;
  archived: boolean;
  students: User[];
}
