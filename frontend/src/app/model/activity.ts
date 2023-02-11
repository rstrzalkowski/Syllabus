export interface ActivityPage {
  content: Activity[],
  totalPages: number,
  last: boolean
}

export interface Activity {
  activityId: number;
  weight: number;
  name: string;
  description: string;
  date: Date;
  createdAt: Date;
  updatedAt: Date;
}
